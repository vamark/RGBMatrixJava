package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static java.awt.Color.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static util.MatrixTestUtil.*;
import static util.SleepUtil.sleepFor;

class CanvasDrawTest extends MatrixTestBase {
    @Test
    void fillColorsWithVsync() {
        List<Color> colors = asList(RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE);

        assertDoesNotThrow(() -> fillMatrixWithVSync(rgbMatrix, colors));
    }

    @Test
    void drawingUsingPreviousCanvasData() {
        assertDoesNotThrow(() -> {
            FrameCanvas canvas = rgbMatrix.createFrameCanvas();
            canvas.fill(BLUE.getRed(), BLUE.getGreen(), BLUE.getBlue());
            canvas = swapAndCopy(rgbMatrix, canvas);

            printMatrixWithVSync(rgbMatrix, canvas, 20, 20, RED, HELLO);
            printMatrixWithVSync(rgbMatrix, canvas, 18, 26, GREEN, WORLD);
        });
    }

    @Test
    void drawPixelsInBatches() {
        Color[] colors = new Color[]{RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE};

        for(Color color : colors) {
            Color[] gradients = createColorGradientArray(BLACK, color, 256);
            assertDoesNotThrow(() -> printColorGradients(rgbMatrix, gradients));
            sleepFor(1000);
        }
    }
}
