package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

public class LedMatrixJNI {
    public static native void initMatrix(Options options, RuntimeOptions runtimeOptions);
    public static native void destroyMatrix();
    public static native void setBrightness(int brightness);
    public static native void setPixel(int row, int col, int r, int g, int b);
    public static native void fill(int r, int g, int b);
    public static native void clear();
}
