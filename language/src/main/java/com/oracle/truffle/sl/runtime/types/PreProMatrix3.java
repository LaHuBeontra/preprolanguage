package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Arrays;

@ExportLibrary(InteropLibrary.class)
public class PreProMatrix3 extends PreProMatrix implements TruffleObject {

    public PreProMatrix3(INDArray ndArray) {
        super(ndArray);
        if (ndArray.shape()[0] != 4 || ndArray.shape()[1] != 3) {
            throw new RuntimeException("The given Matrix4 has the shape "
                    + Arrays.toString(ndArray.shape()) + ", must be [3,3].");
        }
    }

    @TruffleBoundary
    public PreProTimeSeries mul(PreProScalar right) {
        return new PreProMatrix3(multiplyMatrixWithScalar(this, right, 3));
    }

    @TruffleBoundary
    public PreProTimeSeries mul(PreProVector3 right) {
        return new PreProVector3(multiplyMatrixWithVector(this, right, 3));
    }

    @TruffleBoundary
    public PreProTimeSeries mul(PreProMatrix3 right) {
        return new PreProMatrix3(multiplyMatrixWithMatrix(this, right, 3));
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "Matrix3{" + "ndArray=" + getNdArray() + "}";
    }
}
