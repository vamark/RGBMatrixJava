package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.api.Canvas;

public class CanvasJNI implements Canvas {
    private final long nativePointer;

    protected CanvasJNI(long nativePointer) {
        this.nativePointer = nativePointer;
    }

    @Override
    public native int getWidth();

    @Override
    public native int getHeight();

    @Override
    public native void setPixel(int x, int y, int r, int g, int b);

    @Override
    public native void fill(int r, int g, int b);

    @Override
    public native void clear();
}
