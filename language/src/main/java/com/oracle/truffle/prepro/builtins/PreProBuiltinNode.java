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
package com.oracle.truffle.prepro.builtins;

import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.prepro.PreProException;
import com.oracle.truffle.prepro.nodes.PreProExpressionNode;
import com.oracle.truffle.prepro.runtime.PreProContext;
import com.oracle.truffle.prepro.runtime.PreProFunctionRegistry;
import com.oracle.truffle.prepro.runtime.types.PreProConstant;
import com.oracle.truffle.prepro.runtime.types.PreProMatrix;
import com.oracle.truffle.prepro.runtime.types.PreProMatrix3;
import com.oracle.truffle.prepro.runtime.types.PreProMatrix4;
import com.oracle.truffle.prepro.runtime.types.PreProScalar;
import com.oracle.truffle.prepro.runtime.types.PreProVector;
import com.oracle.truffle.prepro.runtime.types.PreProVector3;
import com.oracle.truffle.prepro.runtime.types.PreProVector4;

/**
 * Base class for all builtin functions. It contains the Truffle DSL annotation {@link NodeChild}
 * that defines the function arguments.<br>
 * The builtin functions are registered in {@link PreProContext#installBuiltins}. Every builtin node
 * subclass is instantiated there, wrapped into a function, and added to the
 * {@link PreProFunctionRegistry}. This ensures that builtin functions can be called like user-defined
 * functions; there is no special function lookup or call node for builtin functions.
 */
@NodeChild(value = "arguments", type = PreProExpressionNode[].class)
@GenerateNodeFactory
public abstract class PreProBuiltinNode extends PreProExpressionNode {

    @Override
    public final Object executeGeneric(VirtualFrame frame) {
        try {
            return execute(frame);
        } catch (UnsupportedSpecializationException e) {
            throw PreProException.typeError(e.getNode(), e.getSuppliedValues());
        }
    }

    @Override
    public double executeDouble(VirtualFrame frame) throws UnexpectedResultException {
        return super.executeDouble(frame);
    }

    @Override
    public PreProConstant executePreProConstant(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProConstant(frame);
    }

    @Override
    public PreProScalar executePreProScalar(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProScalar(frame);
    }

    @Override
    public PreProVector4 executePreProVector4(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProVector4(frame);
    }

    @Override
    public PreProVector3 executePreProVector3(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProVector3(frame);
    }

    @Override
    public PreProVector executePreProVector(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProVector(frame);
    }

    @Override
    public PreProMatrix4 executePreProMatrix4(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProMatrix4(frame);
    }

    @Override
    public PreProMatrix3 executePreProMatrix3(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProMatrix3(frame);
    }

    @Override
    public PreProMatrix executePreProMatrix(VirtualFrame frame) throws UnexpectedResultException {
        return super.executePreProMatrix(frame);
    }

    @Override
    public final void executeVoid(VirtualFrame frame) {
        super.executeVoid(frame);
    }

    protected abstract Object execute(VirtualFrame frame);
}
