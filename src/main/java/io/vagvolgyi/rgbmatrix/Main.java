package io.vagvolgyi.rgbmatrix;

import io.vagvolgyi.rgbmatrix.jni.LedMatrixJNI;
import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

import static java.awt.Color.*;

public class Main {
    static {
        System.loadLibrary("rgbmatrixjni");
    }

    public static void main(String[] args) throws InterruptedException {
        Options options = new Options()
                .hardwareMapping("adafruit-hat-pwm")
                .chainLength(1)
                .cols(64)
                .rows(64)
                .ledRgbSequence("BGR");

        RuntimeOptions runtimeOptions = new RuntimeOptions()
                .dropPrivileges(1);

        LedMatrixJNI.initMatrix(options, runtimeOptions);
        LedMatrixJNI.setBrightness(50);
        LedMatrixJNI.fill(BLUE);

        byte[][] helloMatrix = new byte[5][19];
        helloMatrix[0] = new byte[] {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
        helloMatrix[1] = new byte[] {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloMatrix[2] = new byte[] {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloMatrix[3] = new byte[] {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloMatrix[4] = new byte[] {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0};

        LedMatrixJNI.setBrightness(100);
        showMatrix(helloMatrix);
        Thread.sleep(2000);
        LedMatrixJNI.clear();
        LedMatrixJNI.setBrightness(50);
        LedMatrixJNI.fill(RED);

        byte[][] worldMatrix = new byte[5][19];
        worldMatrix[0] = new byte[] {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1};
        worldMatrix[1] = new byte[] {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        worldMatrix[2] = new byte[] {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        worldMatrix[3] = new byte[] {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0};
        worldMatrix[4] = new byte[] {0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1};

        LedMatrixJNI.setBrightness(100);
        showMatrix(worldMatrix);
        Thread.sleep(2000);

        LedMatrixJNI.destroyMatrix();
    }

    private static void showMatrix(byte[][] matrix) throws InterruptedException {
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] == 1) {
                    LedMatrixJNI.setPixel(x, y, WHITE);
                    Thread.sleep(100);
                }
            }
        }
    }
}