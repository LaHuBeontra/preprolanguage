package com.oracle.truffle.sl.types;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Constant extends Variable {
    public Constant(INDArray ndArray) {
        super(ndArray);
    }

    public Constant(double value) {
        this(Nd4j.create(new double[]{value}, new int[]{1, 1}));
    }

    @Override
    public String toString() {
        return "Constant{" + "ndArray=" + getNdArray() + "}";
    }

    public double getDoubleValue() {
        return getNdArray().getDouble(0);
    }

    public Constant add(Constant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only add Constant with double value.");
        }

        return new Constant(getNdArray().add(right.getNdArray()));
    }

    public Constant sub(Constant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only subtract Constant with double value.");
        }

        return new Constant(getNdArray().sub(right.getNdArray()));
    }

    public Constant mul(Constant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only multiply Constant with double value.");
        }

        return new Constant(getNdArray().mul(right.getNdArray()));
    }

    public Constant div(Constant right) {
        if (getNdArray().length() != 1 || right.getNdArray().length() != 1) {
            throw new RuntimeException("Can only divide Constant with double value.");
        }

        return new Constant(getNdArray().div(right.getNdArray()));
    }

    public Vector3 add(Vector3 right) {
        return right.add(this);
    }

    public Vector3 sub(Vector3 right) {
        return right.sub(this);
    }

    public Vector3 mul(Vector3 right) {
        return right.mul(this);
    }

    public Vector3 div(Vector3 right) {
        return right.div(this);
    }
}
