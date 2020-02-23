package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Objects;

@ExportLibrary(InteropLibrary.class)
public class PreProVector implements TruffleObject {

    private final INDArray ndArray;

    public PreProVector(INDArray ndArray) {
        this.ndArray = ndArray;
        if (ndArray.shape().length != 2) {
            throw new RuntimeException("Vector must be one-dimensional.");
        }
    }

    public INDArray timeSeries() {
        return ndArray;
    }

    public int amountTimeElements() {
        return ndArray.shape()[0];
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

    @Override
    @TruffleBoundary
    public String toString() {
        return timeSeries().shapeInfoToString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreProVector)) return false;
        PreProVector that = (PreProVector) o;
        return ndArray.equals(that.ndArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ndArray);
    }
}
