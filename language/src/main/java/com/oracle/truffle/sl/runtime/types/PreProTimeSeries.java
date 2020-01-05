package com.oracle.truffle.sl.runtime.types;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public abstract class PreProTimeSeries {

    private final INDArray ndArray;

    public PreProTimeSeries(INDArray ndArray) {
        this.ndArray = ndArray;
    }

    public INDArray getNdArray() {
        return ndArray;
    }

    public int getAmountTimeElements() {
        return ndArray.shape()[0];
    }

    public PreProTimeSeries add(PreProTimeSeries right) {
        throw new RuntimeException("Cannot add " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProTimeSeries sub(PreProTimeSeries right) {
        throw new RuntimeException("Cannot subtract " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProTimeSeries mul(PreProTimeSeries right) {
        throw new RuntimeException("Cannot multiply " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProTimeSeries div(PreProTimeSeries right) {
        throw new RuntimeException("Cannot divide " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProScalar isEqualTo(PreProTimeSeries right) {
        throw new RuntimeException("Cannot compare " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProScalar isLessThan(PreProTimeSeries right) {
        throw new RuntimeException("Cannot compare " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProScalar isLessOrEqualThan(PreProTimeSeries right) {
        throw new RuntimeException("Cannot compare " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProScalar isGreaterThan(PreProTimeSeries right) {
        throw new RuntimeException("Cannot compare " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    public PreProScalar isGreaterOrEqualThan(PreProTimeSeries right) {
        throw new RuntimeException("Cannot compare " + this.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }

    protected INDArray multiplyMatrixWithMatrix(PreProMatrix left, PreProMatrix right, int dimension) {
        INDArray result = Nd4j.create(left.getAmountTimeElements(), dimension, dimension);
        for (int i = 0; i < left.getAmountTimeElements(); i++) {
            result.putRow(i, left.getNdArray().getRow(i).mmul(right.getNdArray().getRow(i)));
        }
        return result;
    }

    protected INDArray multiplyMatrixWithVector(PreProMatrix left, PreProVector right, int dimension) {
        INDArray result = Nd4j.create(left.getAmountTimeElements(), dimension);
        for (int i = 0; i < left.getAmountTimeElements(); i++) {
            INDArray leftValue = left.getNdArray().getRow(i);
            INDArray rightValue = right.getNdArray().getRow(i);

            INDArray value = leftValue.mmul(rightValue.transpose());
            result.putRow(i, value);
        }

        return result;
    }

    protected INDArray multiplyMatrixWithScalar(PreProMatrix left, PreProScalar right, int dimension) {
        INDArray result = Nd4j.create(left.getAmountTimeElements(), dimension, dimension);
        return multiplyValues(left.getNdArray(), right.getNdArray(), left.getAmountTimeElements(), result);
    }

    protected INDArray multiplyVectorWithScalar(PreProVector left, PreProScalar right, int dimension) {
        INDArray result = Nd4j.create(left.getAmountTimeElements(), dimension);
        return multiplyValues(left.getNdArray(), right.getNdArray(), left.getAmountTimeElements(), result);
    }

    private INDArray multiplyValues(INDArray left, INDArray right, int amountTimeElements, INDArray result) {
        for (int i = 0; i < amountTimeElements; i++) {
            INDArray leftValue = left.getRow(i);
            double rightValue = right.getDouble(i);

            INDArray value = leftValue.mul(rightValue);
            result.putRow(i, value);
        }

        return result;
    }
}
