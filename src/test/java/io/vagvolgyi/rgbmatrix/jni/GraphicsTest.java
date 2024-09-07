package io.vagvolgyi.rgbmatrix.jni;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static util.MatrixTestUtil.loadFont;
import static util.SleepUtil.sleepFor;

class GraphicsTest extends MatrixTestBase {
    @Test
    void drawText() {
        assertDoesNotThrow(() -> {
            FontJNI font1 = loadFont("5x8");
            GraphicsJNI.drawText(rgbMatrix, font1, 0, 8, RED, "Test");
            sleepFor(1000);

            FontJNI font2 = loadFont("7x13");
            GraphicsJNI.drawText(rgbMatrix, font2, 0, 22, GREEN, "Test");
            sleepFor(1000);

            FontJNI font3 = loadFont("9x18");
            GraphicsJNI.drawText(rgbMatrix, font3, 0, 42, BLUE, "Test");
            sleepFor(1000);
        });
    }

    @Test
    void drawCircle() {
        assertDoesNotThrow(() -> {
            GraphicsJNI.drawCircle(rgbMatrix, 32, 32, 10, RED);
            sleepFor(1000);

            GraphicsJNI.drawCircle(rgbMatrix, 32, 32, 20, GREEN);
            sleepFor(1000);

            GraphicsJNI.drawCircle(rgbMatrix, 32, 32, 30, BLUE);
            sleepFor(1000);
        });
    }

    @Test
    void drawLine() {
        assertDoesNotThrow(() -> {
            GraphicsJNI.drawLine(rgbMatrix, 0, 0, 63, 63, PINK);
            sleepFor(500);

            GraphicsJNI.drawLine(rgbMatrix, 0, 63, 63, 0, ORANGE);
            sleepFor(500);

            GraphicsJNI.drawLine(rgbMatrix, 0, 0, 63, 0, YELLOW);
            sleepFor(500);

            GraphicsJNI.drawLine(rgbMatrix, 0, 0, 0, 63, CYAN);
            sleepFor(500);

            GraphicsJNI.drawLine(rgbMatrix, 63, 0, 63, 63, MAGENTA);
            sleepFor(500);

            GraphicsJNI.drawLine(rgbMatrix, 0, 63, 63, 63, WHITE);
            sleepFor(500);
        });
    }
}
