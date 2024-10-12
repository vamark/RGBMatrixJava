package dev.vagvolgyi.rgbmatrix.wrapper;

import dev.vagvolgyi.rgbmatrix.jni.FontJNI;
import dev.vagvolgyi.rgbmatrix.jni.GraphicsJNI;
import dev.vagvolgyi.rgbmatrix.jni.api.Canvas;

import java.awt.*;

class Graphics {
    public boolean setImage(Canvas canvas, int canvasOffsetX, int canvasOffsetY, byte[] imgeBuffer, long bufferSizeBytes,
                            int imageWidth, int imageHeight, boolean isBgr) {
        return GraphicsJNI.setImage(canvas, canvasOffsetX, canvasOffsetY, imgeBuffer, bufferSizeBytes, imageWidth,
                                    imageHeight, isBgr);
    }

    public int drawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text) {
        return GraphicsJNI.drawText(canvas, font, x, y, color, text);
    }

    public int drawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text, int kerningOffset) {
        return GraphicsJNI.drawText(canvas, font, x, y, color, null, text, kerningOffset);
    }

    public int drawText(Canvas canvas, FontJNI font, int x, int y, Color color, Color backgroundColor, String text,
                        int kerningOffset) {
        return GraphicsJNI.drawText(canvas, font, x, y, color, backgroundColor, text, kerningOffset);
    }

    public int verticalDrawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text) {
        return GraphicsJNI.verticalDrawText(canvas, font, x, y, color, text, 0);
    }

    public int verticalDrawText(Canvas canvas, FontJNI font, int x, int y, Color color, String text,
                                       int kerningOffset) {
        return GraphicsJNI.verticalDrawText(canvas, font, x, y, color, null, text, kerningOffset);
    }

    public int verticalDrawText(Canvas canvas, FontJNI font, int x, int y, Color color, Color backgroundColor, String text
            , int kerningOffset) {
        return GraphicsJNI.verticalDrawText(canvas, font, x, y, color, backgroundColor, text, kerningOffset);
    }

    public void drawCircle(Canvas canvas, int x, int y, int radius, Color color) {
        GraphicsJNI.drawCircle(canvas, x, y, radius, color);
    }

    public void drawLine(Canvas canvas, int x1, int y1, int x2, int y2, Color color) {
        GraphicsJNI.drawLine(canvas, x1, y1, x2, y2, color);
    }
}
