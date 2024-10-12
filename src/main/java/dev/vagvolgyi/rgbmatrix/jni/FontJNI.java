package dev.vagvolgyi.rgbmatrix.jni;

public class FontJNI {
    private final long nativePointer;

    public FontJNI(String fontPath) {
        this(loadFont(fontPath));
    }

    private FontJNI(long nativePointer) {
        this.nativePointer = nativePointer;
    }

    private static native long loadFont(String fontPath);

    public native int getHeight();

    public native int getBaseline();

    public native int getCharacterWidth(String character);

    public native FontJNI createOutlineFont();
}
