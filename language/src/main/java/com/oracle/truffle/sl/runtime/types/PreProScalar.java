package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

@ExportLibrary(InteropLibrary.class)
public class PreProScalar extends PreProTimeSeries implements TruffleObject {

    public PreProScalar(INDArray ndArray) {
        super(ndArray);
        int size = ndArray.shape()[1];
        if (ndArray.shape().length != 2 || size != 1) {
            throw new RuntimeException("The given Scalar has the size "
                    + size + ", must be 1.");
        }
    }

    @TruffleBoundary
    public PreProTimeSeries add(PreProScalar right) {
        return new PreProScalar(getNdArray().add(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProTimeSeries sub(PreProScalar right) {
        return new PreProScalar(getNdArray().sub(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProTimeSeries mul(PreProScalar right) {
        return new PreProScalar(getNdArray().mul(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProTimeSeries div(PreProScalar right) {
        return new PreProScalar(getNdArray().div(right.getNdArray()));
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "Scalar{" + "ndArray=" + getNdArray() + "}";
    }

    @TruffleBoundary
    public PreProScalar isEqualTo(PreProScalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) == right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }
        return new PreProScalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isLessThan(PreProScalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) < right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isLessOrEqualThan(PreProScalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) <= right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isGreaterThan(PreProScalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) > right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isGreaterOrEqualThan(PreProScalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) >= right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }
}
