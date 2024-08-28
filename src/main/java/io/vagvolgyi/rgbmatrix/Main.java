package io.vagvolgyi.rgbmatrix;

import io.vagvolgyi.rgbmatrix.jni.FontJNI;
import io.vagvolgyi.rgbmatrix.jni.GraphicsJNI;
import io.vagvolgyi.rgbmatrix.jni.RGBMatrixJNI;
import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

import java.net.URL;

import static java.awt.Color.*;

public class Main {
    static {
        System.loadLibrary("rgbmatrixjni");
    }

    public static void main(String[] args) throws InterruptedException {
        FontJNI font5x8 = new FontJNI(getFontPath("5x8"));
        FontJNI font8x13 = new FontJNI(getFontPath("8x13"));

        Options options = new Options()
                .hardwareMapping("adafruit-hat-pwm")
                .chainLength(1)
                .cols(64)
                .rows(64)
                .ledRgbSequence("BGR");

        RuntimeOptions runtimeOptions = new RuntimeOptions()
                .dropPrivileges(0);

        try(RGBMatrixJNI matrix = new RGBMatrixJNI(options, runtimeOptions)) {
            matrix.setBrightness(60);
            matrix.fill(BLUE.getRed(), BLUE.getGreen(), BLUE.getBlue());
            GraphicsJNI.drawLine(matrix, 0, 0, 63, 63, RED);
            GraphicsJNI.drawLine(matrix, 0, 63, 63, 0, RED);
            GraphicsJNI.drawCircle(matrix, 32, 32, 16, GREEN);

            byte[][] helloArray = new byte[5][19];
            helloArray[0] = new byte[]{1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
            helloArray[1] = new byte[]{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
            helloArray[2] = new byte[]{1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
            helloArray[3] = new byte[]{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
            helloArray[4] = new byte[]{1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0};

            matrix.setBrightness(100);
            GraphicsJNI.drawText(matrix, font5x8, 20, 59, MAGENTA, "Test");
            showMatrix(matrix, helloArray);
            Thread.sleep(2000);
            matrix.clear();
            matrix.setBrightness(60);
            matrix.fill(RED.getRed(), RED.getGreen(), RED.getBlue());
            GraphicsJNI.drawLine(matrix, 0, 0, 63, 63, BLUE);
            GraphicsJNI.drawLine(matrix, 0, 63, 63, 0, BLUE);
            GraphicsJNI.drawCircle(matrix, 32, 32, 16, YELLOW);

            byte[][] worldArray = new byte[5][23];
            worldArray[0] = new byte[]{1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1};
            worldArray[1] = new byte[]{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
            worldArray[2] = new byte[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
            worldArray[3] = new byte[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0};
            worldArray[4] = new byte[]{0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1};

            matrix.setBrightness(100);
            GraphicsJNI.drawText(matrix, font8x13, 18, 59, CYAN, "Test");
            showMatrix(matrix, worldArray);
            Thread.sleep(2000);
        }
    }

    private static void showMatrix(RGBMatrixJNI matrix, byte[][] byteMatrix) throws InterruptedException {
        for(int y = 0; y < byteMatrix.length; y++) {
            for(int x = 0; x < byteMatrix[y].length; x++) {
                if(byteMatrix[y][x] == 1) {
                    matrix.setPixel(x + 20, y + 5, WHITE.getRed(), WHITE.getGreen(), WHITE.getBlue());
                    Thread.sleep(100);
                }
            }
        }
    }

    private static String getFontPath(String fontName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("fonts/" + fontName + ".bdf");
        if(resource == null) {
            throw new IllegalArgumentException("Font not found! " + fontName);
        }
        else {
            return resource.getFile();
        }
    }
}