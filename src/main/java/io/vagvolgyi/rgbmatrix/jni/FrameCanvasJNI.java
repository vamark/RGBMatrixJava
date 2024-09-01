package io.vagvolgyi.rgbmatrix.jni;

import java.awt.*;

public class FrameCanvasJNI extends CanvasJNI {
    protected FrameCanvasJNI(long nativePointer) {
        super(nativePointer);
    }

    public native boolean setPWMBits(int value);

    public native int getPWMBits();

    public native void setLuminanceCorrect(boolean on);

    public native boolean isLuminanceCorrect();

    public native void setBrightness(int brightness);

    public native int getBrightness();

    public native void copyFrom(FrameCanvasJNI other);

    public native void setPixels(int x, int y, int width, int height, Color[] colors);
}
