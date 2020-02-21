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

import com.oracle.truffle.sl.PreProLanguage;
import com.oracle.truffle.sl.runtime.types.PreProVector3;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import static org.junit.Assert.assertEquals;

public class PolyglotCrossProductTest {

    private Context context;
    private Value executionResult;
    private INDArray vector3One;
    private INDArray vector3Two;
    private INDArray vector3Three;

    @Before
    public void setUp() {
        context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).build();
        vector3One = Nd4j.create(new double[]{42, 0, 0, 1, 0, 0, 5, 0, 0, 6, 0, 0, 1, 2, 3}, new int[]{4, 3});
        vector3Two = Nd4j.create(new double[]{43, 0, 0, 44662, 0, 0, 6, 0, 0, 7, 0, 0}, new int[]{4, 3});
        vector3Three = Nd4j.create(new double[]{42, 0, 1, 1, 0, 1, 5, 0, 1, 6, 0, 1}, new int[]{4, 3});
    }

    @After
    public void tearDown() {
        context.close();
    }

//    @Test
//    public void checkX() {
//        String preProScript = "function main() {" +
//                "vec3 p1 = import(\"boundVar\");" +
//                "vec3 x = calculateDifference(p1, import(\"vector3Two\"));" +
//                "vec3 s = calculateDifference(p1, import(\"vector3Three\"));" +
//                "vec3 y = s X x;" +
//                "vec3 z = y X x;" +
//                "export(\"x\", x);" +
//                "export(\"y\", y);" +
//                "export(\"z\", z);" +
//                "}" +
//                "function calculateDifference(vec3 p1, vec3 p2) returns vec3 {" +
//                "return p2 - p1;" +
//                "}";
//        context.eval(PreProLanguage.ID, preProScript);
//        context.getPolyglotBindings().putMember("boundVar", new PreProVector3(vector3One));
//        context.getPolyglotBindings().putMember("vector3Two", new PreProVector3(vector3Two));
//        context.getPolyglotBindings().putMember("vector3Three", new PreProVector3(vector3Three));
//        executionResult = context.getBindings(PreProLanguage.ID).getMember("main").execute();
//        Value xValue = executionResult.getContext().getPolyglotBindings().getMember("x");
//        assertEquals("Vector", xValue.getMetaObject().toString());
//    }
}
