package io.vagvolgyi.rgbmatrix.jni;

import java.awt.*;

public class GraphicsJNI {
    public static native int drawText(FontJNI font, int x, int y, Color color, String text);
    public static native void drawCircle(int x, int y, int radius, Color color);
    public static native void drawLine(int x1, int y1, int x2, int y2, Color color);
}
