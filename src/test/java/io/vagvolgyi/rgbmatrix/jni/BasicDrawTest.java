package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

class BasicDrawTest {
    private static final int MATRIX_WIDTH = 64;
    private static final int MATRIX_HEIGHT = 64;

    static {
        System.loadLibrary("rgbmatrixjni");
    }

    private RGBMatrixJNI rgbMatrix;

    @BeforeEach
    void setUp() {
        Options options = new Options()
                .hardwareMapping("adafruit-hat-pwm")
                .disableHardwarePulsing(true) // Required for the tests to work without root privileges
                .chainLength(1)
                .cols(MATRIX_WIDTH)
                .rows(MATRIX_HEIGHT)
                .ledRgbSequence("BGR");

        RuntimeOptions runtimeOptions = new RuntimeOptions()
                .dropPrivileges(0);

        rgbMatrix = new RGBMatrixJNI(options, runtimeOptions);
    }

    @AfterEach
    void tearDown() {
        rgbMatrix.close();
    }

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
    void fillWithDifferentBrightness(int brightness) throws InterruptedException {
        rgbMatrix.setBrightness(brightness);
        rgbMatrix.fill(WHITE.getRed(), WHITE.getGreen(), WHITE.getBlue());
        sleepFor(500);

        assertThat(rgbMatrix.getBrightness()).isEqualTo(brightness);
    }
}