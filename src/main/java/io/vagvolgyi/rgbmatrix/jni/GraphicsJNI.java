package io.vagvolgyi.rgbmatrix.jni;

import java.awt.Color;
import io.vagvolgyi.rgbmatrix.jni.api.Canvas;

public class GraphicsJNI {
    private GraphicsJNI() {}

    public static native boolean setImage(Canvas canvas, int canvasOffsetX, int canvasOffsetY, byte[] imgeBuffer,
                                          long bufferSizeBytes, int imageWidth, int imageHeight, boolean isBgr);

    public static int drawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text) {
        return drawText(canvas, font, x, y, color, text, 0);
    }

    public static int drawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text, int kerningOffset) {
        return drawText(canvas, font, x, y, color, null, text, kerningOffset);
    }

    public static native int drawText(Canvas canvas, FontJNI font, int x, int y, Color color, Color backgroundColor,
                                      String text, int kerningOffset);

    public static int verticalDrawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text) {
        return verticalDrawText(canvas, font, x, y, color, text, 0);
    }

    public static int verticalDrawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text, int kerningOffset) {
        return verticalDrawText(canvas, font, x, y, color, null, text, kerningOffset);
    }

    public static native int verticalDrawText(Canvas canvas, FontJNI font, int x, int y, Color color,
                                              Color backgroundColor, String text, int kerningOffset);

    public static native void drawCircle(Canvas canvas, int x, int y, int radius, Color color);

    public static native void drawLine(Canvas canvas, int x1, int y1, int x2, int y2, Color color);
}
