package io.vagvolgyi.rgbmatrix.jni;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.Color;
import java.util.List;

import static java.awt.Color.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static util.MatrixTestUtil.*;
import static util.SleepUtil.sleepFor;

class BasicDrawTest extends MatrixTestBase {
    @Test
    void drawPixelByPixel() {
        assertDoesNotThrow(() -> printMatrix(rgbMatrix, 20, 20, RED, HELLO));
        assertDoesNotThrow(() -> printMatrix(rgbMatrix, 18, 26, GREEN, WORLD));
    }

    @Test
    void fillAndClearEntireMatrix() {
        List<Color> colors = asList(RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE);

        assertDoesNotThrow(() -> fillAndClearMatrix(rgbMatrix, colors));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100})
    void fillWithDifferentBrightness(int brightness) {
        rgbMatrix.setBrightness(brightness);
        rgbMatrix.fill(WHITE.getRed(), WHITE.getGreen(), WHITE.getBlue());
        sleepFor(500);

        assertThat(rgbMatrix.getBrightness()).isEqualTo(brightness);
    }
}