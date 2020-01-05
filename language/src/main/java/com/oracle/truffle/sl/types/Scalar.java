package com.oracle.truffle.sl.types;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Scalar extends Variable {
    public Scalar(INDArray ndArray) {
        super(ndArray);
        int size = ndArray.shape()[1];
        if (ndArray.shape().length != 2 || size != 1) {
            throw new RuntimeException("The given Scalar has the size "
                    + size + ", must be 1.");
        }
    }

    public Variable add(Scalar right) {
        return new Scalar(getNdArray().add(right.getNdArray()));
    }

    public Variable sub(Scalar right) {
        return new Scalar(getNdArray().sub(right.getNdArray()));
    }

    public Variable mul(Scalar right) {
        return new Scalar(getNdArray().mul(right.getNdArray()));
    }

    public Variable div(Scalar right) {
        return new Scalar(getNdArray().div(right.getNdArray()));
    }

    @Override
    public String toString() {
        return "Scalar{" + "ndArray=" + getNdArray() + "}";
    }

    public Scalar isEqualTo(Scalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) == right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }
        return new Scalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    public Scalar isLessThan(Scalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) < right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new Scalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    public Scalar isLessOrEqualThan(Scalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) <= right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new Scalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    public Scalar isGreaterThan(Scalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) > right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new Scalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }

    public Scalar isGreaterOrEqualThan(Scalar right) {
        double[] result = new double[getAmountTimeElements()];
        for (int i = 0; i < getAmountTimeElements(); i++) {
            if (getNdArray().getDouble(i) >= right.getNdArray().getDouble(i)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

        }

        return new Scalar(Nd4j.create(result, new int[]{getAmountTimeElements(), 1}));
    }
}
