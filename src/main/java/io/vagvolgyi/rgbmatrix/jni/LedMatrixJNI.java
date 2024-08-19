package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

import java.awt.*;

public class LedMatrixJNI {
    public static native void initMatrix(Options options, RuntimeOptions runtimeOptions);
    public static native void destroyMatrix();
    public static native void setBrightness(int brightness);
    public static native void setPixel(int x, int y, Color color);
    public static native void fill(Color color);
    public static native void clear();
}
