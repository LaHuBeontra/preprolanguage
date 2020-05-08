/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Scope;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Layout;
import com.oracle.truffle.api.object.Shape;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.sl.PreProLanguage;
import com.oracle.truffle.sl.builtins.PreProBuiltinNode;
import com.oracle.truffle.sl.builtins.PreProExportBuiltinFactory;
import com.oracle.truffle.sl.builtins.PreProImportBuiltinFactory;
import com.oracle.truffle.sl.builtins.PreProPrintBuiltin;
import com.oracle.truffle.sl.builtins.PreProPrintBuiltinFactory;
import com.oracle.truffle.sl.builtins.PreProThrowBuiltinFactory;
import com.oracle.truffle.sl.nodes.PreProExpressionNode;
import com.oracle.truffle.sl.nodes.PreProRootNode;
import com.oracle.truffle.sl.nodes.local.PreProReadArgumentNode;
import com.oracle.truffle.sl.runtime.types.PreProConstant;
import com.oracle.truffle.sl.runtime.types.PreProMatrix;
import com.oracle.truffle.sl.runtime.types.PreProScalar;
import com.oracle.truffle.sl.runtime.types.PreProVector;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * The run-time state of PrePro during execution. The context is created by the {@link PreProLanguage}. It
 * is used, for example, by {@link PreProBuiltinNode builtin functions}.
 * <p>
 * It would be an error to have two different context instances during the execution of one script.
 * However, if two separate scripts run in one Java VM at the same time, they have a different
 * context. Therefore, the context is not a singleton.
 */
public final class PreProContext {

    private static final Source BUILTIN_SOURCE = Source.newBuilder(PreProLanguage.ID, "", "PrePro builtin").build();
    private static final Layout LAYOUT = Layout.createLayout();

    private final Env env;
    private final PrintWriter output;
    private final PreProFunctionRegistry functionRegistry;
    private final Shape emptyShape;
    private final PreProLanguage language;
    private final AllocationReporter allocationReporter;
    private final Iterable<Scope> topScopes; // Cache the top scopes

    public PreProContext(PreProLanguage language, TruffleLanguage.Env env, List<NodeFactory<? extends PreProBuiltinNode>> externalBuiltins) {
        this.env = env;
        this.output = new PrintWriter(env.out(), true);
        this.language = language;
        this.allocationReporter = env.lookup(AllocationReporter.class);
        this.functionRegistry = new PreProFunctionRegistry(language);
        this.topScopes = Collections.singleton(Scope.newBuilder("global", functionRegistry.getFunctionsObject()).build());
        installBuiltins();
        for (NodeFactory<? extends PreProBuiltinNode> builtin : externalBuiltins) {
            installBuiltin(builtin);
        }
        this.emptyShape = LAYOUT.createShape(SLObjectType.SINGLETON);
    }

    /**
     * Return the current Truffle environment.
     */
    public Env getEnv() {
        return env;
    }

    /**
     * The default default, i.e., the output for the {@link PreProPrintBuiltin}. To allow unit
     * testing, we do not use {@link System#out} directly.
     */
    public PrintWriter getOutput() {
        return output;
    }

    /**
     * Returns the registry of all functions that are currently defined.
     */
    public PreProFunctionRegistry getFunctionRegistry() {
        return functionRegistry;
    }

    public Iterable<Scope> getTopScopes() {
        return topScopes;
    }

    /**
     * Adds all builtin functions to the {@link PreProFunctionRegistry}. This method lists all
     * {@link PreProBuiltinNode builtin implementation classes}.
     */
    private void installBuiltins() {
        installBuiltin(PreProPrintBuiltinFactory.getInstance());
        installBuiltin(PreProThrowBuiltinFactory.getInstance());
        installBuiltin(PreProImportBuiltinFactory.getInstance());
        installBuiltin(PreProExportBuiltinFactory.getInstance());
    }

    private void installBuiltin(NodeFactory<? extends PreProBuiltinNode> factory) {
        /*
         * The builtin node factory is a class that is automatically generated by the Truffle DSL.
         * The signature returned by the factory reflects the signature of the @Specialization
         *
         * methods in the builtin classes.
         */
        int argumentCount = factory.getExecutionSignature().size();
        PreProExpressionNode[] argumentNodes = new PreProExpressionNode[argumentCount];
        /*
         * Builtin functions are like normal functions, i.e., the arguments are passed in as an
         * Object[] array encapsulated in PreProArguments. A PreProReadArgumentNode extracts a parameter
         * from this array.
         */
        for (int i = 0; i < argumentCount; i++) {
            argumentNodes[i] = new PreProReadArgumentNode(i);
        }
        /* Instantiate the builtin node. This node performs the actual functionality. */
        PreProBuiltinNode builtinBodyNode = factory.createNode((Object) argumentNodes);
        builtinBodyNode.addRootTag();
        /* The name of the builtin function is specified via an annotation on the node class. */
        String name = lookupNodeInfo(builtinBodyNode.getClass()).shortName();
        builtinBodyNode.setUnavailableSourceSection();

        /* Wrap the builtin in a RootNode. Truffle requires all AST to start with a RootNode. */
        PreProRootNode rootNode = new PreProRootNode(language, new FrameDescriptor(), builtinBodyNode, BUILTIN_SOURCE.createUnavailableSection(), name);

        /* Register the builtin function in our function registry. */
        getFunctionRegistry().register(name, Truffle.getRuntime().createCallTarget(rootNode));
    }

    public static NodeInfo lookupNodeInfo(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        NodeInfo info = clazz.getAnnotation(NodeInfo.class);
        if (info != null) {
            return info;
        } else {
            return lookupNodeInfo(clazz.getSuperclass());
        }
    }

    /*
     * Methods for object creation / object property access.
     */
    public AllocationReporter getAllocationReporter() {
        return allocationReporter;
    }

    /**
     * Allocate an empty object. All new objects initially have no properties. Properties are added
     * when they are first stored, i.e., the store triggers a shape change of the object.
     */
    public DynamicObject createObject(AllocationReporter reporter) {
        DynamicObject object;
        reporter.onEnter(null, 0, AllocationReporter.SIZE_UNKNOWN);
        object = emptyShape.newInstance();
        reporter.onReturnValue(object, 0, AllocationReporter.SIZE_UNKNOWN);
        return object;
    }

    public static boolean isPreProObject(Object value) {
        /*
         * LAYOUT.getType() returns a concrete implementation class, i.e., a class that is more
         * precise than the base class DynamicObject. This makes the type check faster.
         */
        return LAYOUT.getType().isInstance(value) && LAYOUT.getType().cast(value).getShape().getObjectType() == SLObjectType.SINGLETON;
    }

    /*
     * Methods for language interoperability.
     */

    public static Object fromForeignValue(Object a) {
        if (a instanceof Double || a instanceof PreProConstant || a instanceof PreProScalar
                || a instanceof PreProMatrix || a instanceof PreProVector) {
            return a;
        } else if (a instanceof Character) {
            return String.valueOf(a);
        } else if (a instanceof Number) {
            return fromForeignNumber(a);
        } else if (a instanceof TruffleObject) {
            return a;
        } else if (a instanceof PreProContext) {
            return a;
        }
        CompilerDirectives.transferToInterpreter();
        throw new IllegalStateException(a + " is not a Truffle value");
    }

    @TruffleBoundary
    private static PreProConstant fromForeignNumber(Object a) {
        return new PreProConstant((Double) a);
    }

    /**
     * Exports an object to the polyglot bindings
     * to access it within a PrePro program.
     */
    public void exportSymbol(String symbolName, Object value) {
        env.exportSymbol(symbolName, value);
    }

    /**
     * Imports an object that was exported to the
     * polyglot bindings from within a PrePro program.
     */
    public Object importSymbol(String symbolName) {
        return env.importSymbol(symbolName);
    }
}
