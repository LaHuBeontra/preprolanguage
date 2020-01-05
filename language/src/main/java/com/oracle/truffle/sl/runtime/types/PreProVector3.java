package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;

@ExportLibrary(InteropLibrary.class)
public class PreProVector3 extends PreProVector implements TruffleObject {

    public PreProVector3(INDArray ndArray) {
        super(ndArray);
        int size = ndArray.shape()[1];
        if (ndArray.shape().length != 2 || size != 3) {
            throw new RuntimeException("The given Vector3 has the size "
                    + size + ", must be 3.");
        }
    }

    @TruffleBoundary
    public PreProTimeSeries add(PreProVector3 right) {
        return new PreProVector3(getNdArray().add(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProTimeSeries sub(PreProVector3 right) {
        return new PreProVector3(getNdArray().sub(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProTimeSeries mul(PreProScalar right) {
        return new PreProVector3(multiplyVectorWithScalar(this, right, 3));
    }

    @TruffleBoundary
    public PreProTimeSeries mul(PreProVector3 right) {
        return new PreProVector3(getNdArray().mul(right.getNdArray()));
    }

    @TruffleBoundary
    public PreProVector3 add(PreProConstant right) {
        return new PreProVector3(getNdArray().add(right.getNdArray().getDouble(0)));
    }

    @TruffleBoundary
    public PreProVector3 sub(PreProConstant right) {
        return new PreProVector3(getNdArray().sub(right.getNdArray().getDouble(0)));
    }

    @TruffleBoundary
    public PreProVector3 mul(PreProConstant right) {
        return new PreProVector3(getNdArray().mul(right.getNdArray().getDouble(0)));
    }

    @TruffleBoundary
    public PreProVector3 div(PreProConstant right) {
        return new PreProVector3(getNdArray().div(right.getNdArray().getDouble(0)));
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "Vector3{" + "ndArray=" + getNdArray() + "}";
    }
}
