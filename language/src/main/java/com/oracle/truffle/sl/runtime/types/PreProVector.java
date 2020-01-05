package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;

@ExportLibrary(InteropLibrary.class)
public class PreProVector extends PreProTimeSeries implements TruffleObject {
    public PreProVector(INDArray ndArray) {
        super(ndArray);
        if (ndArray.shape().length != 2) {
            throw new RuntimeException("Vector must be one-dimensional.");
        }
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "Vector{" + "ndArray=" + getNdArray() + "}";
    }
}
