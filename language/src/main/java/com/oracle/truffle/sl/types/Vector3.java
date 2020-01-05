package com.oracle.truffle.sl.types;

import org.nd4j.linalg.api.ndarray.INDArray;

public class Vector3 extends Vector {
    public Vector3(INDArray ndArray) {
        super(ndArray);
        int size = ndArray.shape()[1];
        if (ndArray.shape().length != 2 || size != 3) {
            throw new RuntimeException("The given Vector3 has the size "
                    + size + ", must be 3.");
        }
    }

    public Variable add(Vector3 right) {
        return new Vector3(getNdArray().add(right.getNdArray()));
    }

    public Variable sub(Vector3 right) {
        return new Vector3(getNdArray().sub(right.getNdArray()));
    }

    public Variable mul(Scalar right) {
        return new Vector3(multiplyVectorWithScalar(this, right, 3));
    }

    public Variable mul(Vector3 right) {
        return new Vector3(getNdArray().mul(right.getNdArray()));
    }

    public Vector3 add(Constant right) {
        return new Vector3(getNdArray().add(right.getNdArray().getDouble(0)));
    }

    public Vector3 sub(Constant right) {
        return new Vector3(getNdArray().sub(right.getNdArray().getDouble(0)));
    }

    public Vector3 mul(Constant right) {
        return new Vector3(getNdArray().mul(right.getNdArray().getDouble(0)));
    }

    public Vector3 div(Constant right) {
        return new Vector3(getNdArray().div(right.getNdArray().getDouble(0)));
    }

    @Override
    public String toString() {
        return "Vector3{" + "ndArray=" + getNdArray() + "}";
    }
}
