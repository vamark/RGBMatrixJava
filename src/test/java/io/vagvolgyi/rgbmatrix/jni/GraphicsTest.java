package io.vagvolgyi.rgbmatrix.jni;

import org.junit.jupiter.api.Test;
import util.MatrixTestUtil;

import static java.awt.Color.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static util.MatrixTestUtil.loadFont;
import static util.SleepUtil.sleepFor;

class GraphicsTest extends MatrixTestBase {
    @Test
    void setImage() {
        byte[] image = MatrixTestUtil.createImageArray();

        assertDoesNotThrow(() -> {
            GraphicsJNI.setImage(rgbMatrix, 0, 0, image, image.length, 15, 15, false);
            sleepFor(1000);

            GraphicsJNI.setImage(rgbMatrix, 15, 15, image, image.length, 15, 15, true);
            sleepFor(1000);
        });
    }

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
    void advancedDrawText() {
        assertDoesNotThrow(() -> {
            FontJNI font1 = loadFont("5x8");
            GraphicsJNI.drawText(rgbMatrix, font1, 0, 8, BLACK, RED, "Test", 2);
            sleepFor(1000);

            FontJNI font2 = loadFont("7x13");
            GraphicsJNI.drawText(rgbMatrix, font2, 0, 22, BLACK, GREEN, "Test", 0);
            sleepFor(1000);

            FontJNI font3 = loadFont("9x18");
            GraphicsJNI.drawText(rgbMatrix, font3, 0, 42, BLACK, BLUE, "Test", -2);
            sleepFor(1000);
        });
    }

    @Test
    void verticalDrawText() {
        assertDoesNotThrow(() -> {
            FontJNI font1 = loadFont("5x8");
            GraphicsJNI.verticalDrawText(rgbMatrix, font1, 5, 8, RED, "Test");
            sleepFor(1000);

            FontJNI font2 = loadFont("7x13");
            GraphicsJNI.verticalDrawText(rgbMatrix, font2, 14, 13, GREEN, "Test");
            sleepFor(1000);

            FontJNI font3 = loadFont("9x18");
            GraphicsJNI.verticalDrawText(rgbMatrix, font3, 25, 18, BLUE, "Test");
            sleepFor(1000);
        });
    }

    @Test
    void advancedVerticalDrawText() {
        assertDoesNotThrow(() -> {
            FontJNI font1 = loadFont("5x8");
            GraphicsJNI.verticalDrawText(rgbMatrix, font1, 5, 8, BLACK, RED, "Test", 2);
            sleepFor(1000);

            FontJNI font2 = loadFont("7x13");
            GraphicsJNI.verticalDrawText(rgbMatrix, font2, 14, 13, BLACK, GREEN, "Test", 0);
            sleepFor(1000);

            FontJNI font3 = loadFont("9x18");
            GraphicsJNI.verticalDrawText(rgbMatrix, font3, 25, 18, BLACK, BLUE, "Test", -2);
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
