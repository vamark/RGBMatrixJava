package io.vagvolgyi.ledmatrix.jni;

import io.vagvolgyi.ledmatrix.jni.model.Options;
import io.vagvolgyi.ledmatrix.jni.model.RuntimeOptions;

public class RGBMatrixJNI {
    static {
        System.loadLibrary("rgbmatrixjni");
    }

    public static native void initMatrix(Options options, RuntimeOptions runtimeOptions);
    public static native void destroyMatrix();
    public static native void setBrightness(int brightness);
    public static native void setPixel(int row, int col, int r, int g, int b);
    public static native void fill(int r, int g, int b);
    public static native void clear();
}
