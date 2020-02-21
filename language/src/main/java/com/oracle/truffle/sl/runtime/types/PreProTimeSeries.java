package com.oracle.truffle.sl.runtime.types;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

abstract class PreProTimeSeries {

    final INDArray ndArray;

    PreProTimeSeries(INDArray ndArray) {
        this.ndArray = ndArray;
    }

    public INDArray timeSeries() {
        return ndArray;
    }

    public int amountTimeElements() {
        return ndArray.shape()[0];
    }

    INDArray multiplyMatrixWithMatrix(PreProMatrix left, PreProMatrix right, int dimension) {
        INDArray result = Nd4j.create(left.amountTimeElements(), dimension, dimension);
        for (int i = 0; i < left.amountTimeElements(); i++) {
            result.putRow(i, left.timeSeries().getRow(i).mmul(right.timeSeries().getRow(i)));
        }
        return result;
    }

    INDArray multiplyMatrixWithVector(PreProMatrix left, PreProVector right, int dimension) {
        INDArray result = Nd4j.create(left.amountTimeElements(), dimension);
        for (int i = 0; i < left.amountTimeElements(); i++) {
            INDArray leftValue = left.timeSeries().getRow(i);
            INDArray rightValue = right.timeSeries().getRow(i);

            INDArray value = leftValue.mmul(rightValue.transpose());
            result.putRow(i, value);
        }

        return result;
    }

    INDArray multiplyMatrixWithScalar(PreProMatrix left, PreProScalar right, int dimension) {
        INDArray result = Nd4j.create(left.amountTimeElements(), dimension, dimension);
        return multiplyValues(left.timeSeries(), right.timeSeries(), left.amountTimeElements(), result);
    }

    INDArray multiplyVectorWithScalar(PreProVector left, PreProScalar right, int dimension) {
        INDArray result = Nd4j.create(left.amountTimeElements(), dimension);
        return multiplyValues(left.timeSeries(), right.timeSeries(), left.amountTimeElements(), result);
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
