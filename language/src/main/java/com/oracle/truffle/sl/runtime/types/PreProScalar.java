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
    public PreProScalar add(PreProScalar right) {
        return new PreProScalar(timeSeries().add(right.timeSeries()));
    }

    @TruffleBoundary
    public PreProScalar sub(PreProScalar right) {
        return new PreProScalar(timeSeries().sub(right.timeSeries()));
    }

    @TruffleBoundary
    public PreProScalar mul(PreProScalar right) {
        return new PreProScalar(timeSeries().mul(right.timeSeries()));
    }

    @TruffleBoundary
    public PreProScalar div(PreProScalar right) {
        return new PreProScalar(timeSeries().div(right.timeSeries()));
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return timeSeries().shapeInfoToString();
    }

    @TruffleBoundary
    public PreProScalar isEqualTo(PreProScalar right) {
        double[] result = new double[amountTimeElements()];
        for (int i = 0; i < amountTimeElements(); i++) {
            if (timeSeries().getDouble(i) == right.timeSeries().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }
        return new PreProScalar(Nd4j.create(result, new int[]{amountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isLessThan(PreProScalar right) {
        double[] result = new double[amountTimeElements()];
        for (int i = 0; i < amountTimeElements(); i++) {
            if (timeSeries().getDouble(i) < right.timeSeries().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{amountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isLessOrEqualThan(PreProScalar right) {
        double[] result = new double[amountTimeElements()];
        for (int i = 0; i < amountTimeElements(); i++) {
            if (timeSeries().getDouble(i) <= right.timeSeries().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{amountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isGreaterThan(PreProScalar right) {
        double[] result = new double[amountTimeElements()];
        for (int i = 0; i < amountTimeElements(); i++) {
            if (timeSeries().getDouble(i) > right.timeSeries().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{amountTimeElements(), 1}));
    }

    @TruffleBoundary
    public PreProScalar isGreaterOrEqualThan(PreProScalar right) {
        double[] result = new double[amountTimeElements()];
        for (int i = 0; i < amountTimeElements(); i++) {
            if (timeSeries().getDouble(i) >= right.timeSeries().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new PreProScalar(Nd4j.create(result, new int[]{amountTimeElements(), 1}));
    }
}
