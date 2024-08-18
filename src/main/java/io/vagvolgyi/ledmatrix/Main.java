package io.vagvolgyi.ledmatrix;

import io.vagvolgyi.ledmatrix.jni.RGBMatrixJNI;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RGBMatrixJNI.initMatrix();
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