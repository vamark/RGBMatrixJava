package dev.vagvolgyi.rgbmatrix.jni;

import dev.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;

import java.awt.*;

public class FrameCanvasJNI extends CanvasJNI implements FrameCanvas {
    protected FrameCanvasJNI(long nativePointer) {
        super(nativePointer);
    }

    @Override
    public native boolean setPWMBits(int value);

    @Override
    public native int getPWMBits();

    @Override
    public native void setLuminanceCorrect(boolean on);

    @Override
    public native boolean isLuminanceCorrect();

    @Override
    public native void setBrightness(int brightness);

    @Override
    public native int getBrightness();

    @Override
    public native void copyFrom(FrameCanvas other);

    @Override
    public native void setPixels(int x, int y, int width, int height, Color[] colors);
}
