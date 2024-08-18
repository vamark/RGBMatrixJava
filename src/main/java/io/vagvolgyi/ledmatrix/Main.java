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

        byte[][] matrix = new byte[5][19];
        matrix[0] = new byte[] {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1};
        matrix[1] = new byte[] {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        matrix[2] = new byte[] {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        matrix[3] = new byte[] {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0};
        matrix[4] = new byte[] {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1};

        for (int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] == 1) {
                    RGBMatrixJNI.setPixel(x, y, 255, 255, 255);
                    Thread.sleep(100);
                }
            }
        }

        Thread.sleep(5000);
        RGBMatrixJNI.destroyMatrix();
    }
}