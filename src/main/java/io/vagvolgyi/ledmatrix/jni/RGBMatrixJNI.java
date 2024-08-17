package io.vagvolgyi.ledmatrix.jni;

public class RGBMatrixJNI {
    static {
        System.loadLibrary("rgbmatrixjni");
    }

    public static native void initMatrix();
    public static native void destroyMatrix();
    public static native void setPixel(int row, int col, int r, int g, int b);
}
