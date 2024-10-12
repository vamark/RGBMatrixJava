package dev.vagvolgyi.rgbmatrix.jni;

import dev.vagvolgyi.rgbmatrix.jni.model.Options;
import dev.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class MatrixTestBase {
    protected static final int MATRIX_WIDTH = 64;
    protected static final int MATRIX_HEIGHT = 64;

    static {
        System.loadLibrary("rgbmatrixjni");
    }

    protected RGBMatrixJNI rgbMatrix;

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
}
