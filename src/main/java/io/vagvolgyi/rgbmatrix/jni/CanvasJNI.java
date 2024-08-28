package io.vagvolgyi.rgbmatrix.jni;

public class CanvasJNI {
    private final long nativePointer;

    protected CanvasJNI(long nativePointer) {
        this.nativePointer = nativePointer;
    }

    public native int getWidth();

    public native int getHeight();

    public native void setPixel(int x, int y, int r, int g, int b);

    public native void fill(int r, int g, int b);

    public native void clear();
}
