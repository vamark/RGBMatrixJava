package util;

import io.vagvolgyi.rgbmatrix.jni.RGBMatrixJNI;

import java.awt.*;
import java.util.List;

public class MatrixTestUtil {
    public static void printMatrix(RGBMatrixJNI matrix, int xOffset, int yOffset, Color color, byte[][] bytes) throws InterruptedException {
        for(int y = 0; y < bytes.length; y++) {
            for(int x = 0; x < bytes[y].length; x++) {
                if(bytes[y][x] == 1) {
                    matrix.setPixel(x + xOffset, y + yOffset, color.getRed(), color.getGreen(), color.getBlue());
                    Thread.sleep(50);
                }
            }
        }
    }

    public static void fillAndClearMatrix(RGBMatrixJNI matrix, List<Color> colors) throws InterruptedException {
        for(Color color : colors) {
            matrix.fill(color.getRed(), color.getGreen(), color.getBlue());
            Thread.sleep(300);
            matrix.clear();
            Thread.sleep(200);
        }
    }
}
