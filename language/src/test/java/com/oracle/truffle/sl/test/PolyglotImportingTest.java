/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
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
package com.oracle.truffle.sl.test;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.sl.PreProLanguage;
import com.oracle.truffle.sl.runtime.PreProFunction;
import com.oracle.truffle.sl.runtime.types.PreProConstant;
import com.oracle.truffle.sl.runtime.types.PreProMatrix3;
import com.oracle.truffle.sl.runtime.types.PreProMatrix4;
import com.oracle.truffle.sl.runtime.types.PreProVector3;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.lang.reflect.Field;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PolyglotImportingTest {

    private Context context;
    private INDArray constant;
    private INDArray vector3;
    private INDArray matrix3;
    private INDArray matrix4;

    @Before
    public void setUp() {
        context = Context.newBuilder().allowExperimentalOptions(true).allowPolyglotAccess(PolyglotAccess.ALL).allowAllAccess(true).build();
        context.eval(PreProLanguage.ID, "function main() {}"); // initialize context
        context.enter();
        constant = Nd4j.create(new double[]{42}, new int[]{1, 1});
        vector3 = Nd4j.create(new double[]{42, 0, 1, 1, 0, 1, 5, 0, 1, 6, 0, 1}, new int[]{4, 3});
        matrix3 = Nd4j.create(IntStream.range(1, 36 + 1).mapToDouble(i -> i).toArray(), new int[]{4, 3, 3});
        matrix4 = Nd4j.create(IntStream.range(1, 64 + 1).mapToDouble(i -> i).toArray(), new int[]{4, 4, 4});
    }

    @After
    public void tearDown() {
        context.leave();
        context.close();
    }

    @Test
    public void importPreProConstant() {
        PreProConstant preProConstant = new PreProConstant(constant);
        String preProScript = "function bind() returns const { " +
                "return import(\"boundVar\");" +
                "}";
        context.eval(PreProLanguage.ID, preProScript);
        context.getPolyglotBindings().putMember("boundVar", new PreProConstant(constant));
        Value res = context.getBindings(PreProLanguage.ID).getMember("bind").execute();
        assertEquals("Number", res.getMetaObject().toString());
        assertEquals(String.valueOf(preProConstant.getDoubleValue()), res.toString());
    }

    @Test
    public void importPreProVector3() throws Exception {
        PreProVector3 preProVector3 = new PreProVector3(vector3);
        String preProScript = "function bind() returns vec3 { " +
                "return import(\"boundVar\");" +
                "}";
        context.eval(PreProLanguage.ID, preProScript);
        context.getPolyglotBindings().putMember("boundVar", preProVector3);
        Value bindFunctionPolyglotValue = context.getBindings(PreProLanguage.ID).getMember("bind");
        Field bindFunctionReceiver = bindFunctionPolyglotValue.getClass().getDeclaredField("receiver");
        bindFunctionReceiver.setAccessible(true);
        Object bindFunctionObject = bindFunctionReceiver.get(bindFunctionPolyglotValue);

        PreProFunction bindFunction = (PreProFunction) bindFunctionObject;
        Object obj = InteropLibrary.getFactory().getUncached().execute(bindFunction);

        assertTrue(obj instanceof PreProVector3);
        assertEquals(preProVector3.timeSeries(), ((PreProVector3) obj).timeSeries());
    }

    @Test
    public void importPreProMatrix3() {
        PreProMatrix3 preProMatrix3 = new PreProMatrix3(matrix3);
        String preProScript = "function bind() returns mat3 { " +
                "return import(\"boundVar\");" +
                "}";
        context.eval(PreProLanguage.ID, preProScript);
        context.getPolyglotBindings().putMember("boundVar", new PreProMatrix3(matrix3));
        Value res = context.getBindings(PreProLanguage.ID).getMember("bind").execute();
        assertEquals("Matrix", res.getMetaObject().toString());
        assertEquals(preProMatrix3.toString(), res.toString());
    }

    @Test
    public void importPreProMatrix4() {
        PreProMatrix4 preProMatrix4 = new PreProMatrix4(matrix4);
        String preProScript = "function bind() returns mat4 { " +
                "return import(\"boundVar\");" +
                "}";
        context.eval(PreProLanguage.ID, preProScript);
        context.getPolyglotBindings().putMember("boundVar", new PreProMatrix4(matrix4));
        Value res = context.getBindings(PreProLanguage.ID).getMember("bind").execute();
        assertEquals("Matrix", res.getMetaObject().toString());
        assertEquals(preProMatrix4.toString(), res.toString());
    }
}
