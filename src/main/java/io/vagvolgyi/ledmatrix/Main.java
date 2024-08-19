package io.vagvolgyi.ledmatrix;

import io.vagvolgyi.ledmatrix.jni.RGBMatrixJNI;
import io.vagvolgyi.ledmatrix.jni.model.Options;
import io.vagvolgyi.ledmatrix.jni.model.RuntimeOptions;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Options options = new Options()
                .hardwareMapping("adafruit-hat-pwm")
                .chainLength(1)
                .cols(64)
                .rows(64)
                .ledRgbSequence("BGR");

        RuntimeOptions runtimeOptions = new RuntimeOptions()
                .dropPrivileges(1);

        RGBMatrixJNI.initMatrix(options, runtimeOptions);
        RGBMatrixJNI.setBrightness(50);
        RGBMatrixJNI.fill(0, 0, 122);

        byte[][] helloMatrix = new byte[5][19];
        helloMatrix[0] = new byte[] {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
        helloMatrix[1] = new byte[] {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloMatrix[2] = new byte[] {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloMatrix[3] = new byte[] {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloMatrix[4] = new byte[] {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0};

        showMatrix(helloMatrix);
        Thread.sleep(2000);
        RGBMatrixJNI.clear();
        RGBMatrixJNI.fill(122, 0, 0);

        byte[][] worldMatrix = new byte[5][19];
        worldMatrix[0] = new byte[] {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1};
        worldMatrix[1] = new byte[] {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        worldMatrix[2] = new byte[] {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        worldMatrix[3] = new byte[] {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0};
        worldMatrix[4] = new byte[] {0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1};

        showMatrix(worldMatrix);
        Thread.sleep(2000);

        RGBMatrixJNI.destroyMatrix();
    }

    private static void showMatrix(byte[][] matrix) throws InterruptedException {
        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] == 1) {
                    RGBMatrixJNI.setPixel(x, y, 255, 255, 255);
                    Thread.sleep(100);
                }
            }
        }
    }
}