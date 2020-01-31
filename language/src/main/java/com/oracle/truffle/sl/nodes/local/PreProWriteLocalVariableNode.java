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
package com.oracle.truffle.sl.nodes.local;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.sl.PreProException;
import com.oracle.truffle.sl.nodes.PreProExpressionNode;
import com.oracle.truffle.sl.nodes.PreProStatementNode;
import com.oracle.truffle.sl.nodes.util.PreProUnboxNodeGen;
import com.oracle.truffle.sl.runtime.types.PreProConstant;
import com.oracle.truffle.sl.runtime.types.VariableType;

/**
 * Node to write a local variable to a function's {@link VirtualFrame frame}. The Truffle frame API
 * allows to store primitive values of all Java primitive types, and Object values.
 */
@NodeInfo(shortName = "assign", description = "The node implementing an assignment statement")
public final class PreProWriteLocalVariableNode extends PreProStatementNode {

    /**
     * Value to assign the variable. Since PrePro is statically typed, {@link #executeVoid}
     * will throw a type assignment error if types are not compatible.
     */
    @Child
    private PreProExpressionNode valueNode;

    /**
     * The type of the variable.
     */
    private String type;

    private FrameSlot frameSlot;

    public PreProWriteLocalVariableNode(FrameSlot frameSlot, String type, PreProExpressionNode valueNode) {
        this.frameSlot = frameSlot;
        this.type = type;
        this.valueNode = PreProUnboxNodeGen.create(valueNode);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        switch (VariableType.getTypeForText(type)) {
//            case VEC3:
//                break;
//            case VEC4:
//                break;
//            case MAT:
//                break;
//            case MAT3:
//                break;
//            case MAT4:
//                break;
//            case SCAL:
//                break;
            case CONSTANT:
                try {
                    PreProConstant value = valueNode.executePreProConstant(frame);
                    frame.getFrameDescriptor().setFrameSlotKind(frameSlot, FrameSlotKind.Object);
                    frame.setObject(frameSlot, value);
                } catch (UnexpectedResultException e) {
                    throw PreProException.typeError(this, e.getResult());
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + VariableType.getTypeForText(type));
        }
    }

    FrameSlot getSlot() {
        return this.frameSlot;
    }
//
//    /**
//     * Specialized method to write a primitive {@code double} value. This is only possible if the
//     * local variable also has currently the type {@code double} or was never written before,
//     * therefore a Truffle DSL {@link #isDoubleOrIllegal(VirtualFrame) custom guard} is specified.
//     */
//    @Specialization(guards = "isPreProConstantOrIllegal(frame)")
//    protected double writeDouble(VirtualFrame frame, double value) {
//        /* Initialize type on first write of the local variable. No-op if kind is already Long. */
//        frame.getFrameDescriptor().setFrameSlotKind(frameSlot, FrameSlotKind.Double);
//
//        frame.setDouble(frameSlot, value);
//        return value;
//    }
//
//    /**
//     * Generic write method that works for all possible types.
//     * <p>
//     * Since this method takes a value of type {@link Object}, it is guaranteed to never fail,
//     * i.e., once we are in this specialization the node will never be re-specialized.
//     */
//    @Specialization(replaces = {"writeDouble"})
//    protected Object write(VirtualFrame frame, Object value) {
//        /*
//         * Regardless of the type before, the new and final type of the local variable is Object.
//         * Changing the slot kind also discards compiled code, because the variable type is
//         * important when the compiler optimizes a method.
//         *
//         * No-op if kind is already Object.
//         */
//        frame.getFrameDescriptor().setFrameSlotKind(frameSlot, FrameSlotKind.Object);
//
//        frame.setObject(frameSlot, value);
//        return value;
//    }
//
//    protected boolean isDoubleOrIllegal(VirtualFrame frame) {
//        final FrameSlotKind kind = frame.getFrameDescriptor().getFrameSlotKind(frameSlot);
//        return kind == FrameSlotKind.Double || kind == FrameSlotKind.Illegal;
//    }
}
