package com.oracle.truffle.sl.runtime.types;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import org.nd4j.linalg.api.ndarray.INDArray;

@ExportLibrary(InteropLibrary.class)
public class PreProMatrix extends PreProTimeSeries implements TruffleObject {
    public PreProMatrix(INDArray ndArray) {
        super(ndArray);
        if (ndArray.shape().length != 3) {
            throw new RuntimeException("Matrix must be two-dimensional.");
        }
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "Matrix{" + "ndArray=" + getNdArray() + "}";
    }
}
