package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

@ExportLibrary(InteropLibrary.class)
public final class PreProConstant extends PreProTimeSeries implements TruffleObject {

    public PreProConstant(INDArray ndArray) {
        super(ndArray);
    }

    public PreProConstant(double value) {
        this(Nd4j.create(new double[]{value}, new int[]{1, 1}));
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "Constant{" + "ndArray=" + getNdArray() + "}";
    }

    public double getDoubleValue() {
        return getNdArray().getDouble(0);
    }

    @TruffleBoundary
    public PreProConstant add(PreProConstant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only add Constant with double value.");
        }

        return new PreProConstant(getNdArray().add(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProConstant sub(PreProConstant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only subtract Constant with double value.");
        }

        return new PreProConstant(getNdArray().sub(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProConstant mul(PreProConstant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only multiply Constant with double value.");
        }

        return new PreProConstant(getNdArray().mul(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProConstant div(PreProConstant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only divide Constant with double value.");
        }

        return new PreProConstant(getNdArray().div(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProVector3 add(PreProVector3 right) {
        return right.add(this);
    }

    @TruffleBoundary
    public PreProVector3 sub(PreProVector3 right) {
        return right.sub(this);
    }

    @TruffleBoundary
    public PreProVector3 mul(PreProVector3 right) {
        return right.mul(this);
    }

    @TruffleBoundary
    public PreProVector3 div(PreProVector3 right) {
        return right.div(this);
    }
}
