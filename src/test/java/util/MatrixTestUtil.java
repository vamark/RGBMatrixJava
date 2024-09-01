package util;

import io.vagvolgyi.rgbmatrix.jni.FrameCanvasJNI;
import io.vagvolgyi.rgbmatrix.jni.RGBMatrixJNI;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MatrixTestUtil {
    public static final byte[][] HELLO = createHelloArray();
    public static final byte[][] WORLD = createWorldArray();

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

    public static void printMatrixWithVSync(RGBMatrixJNI rgbMatrix, FrameCanvasJNI canvas, int xOffset, int yOffset, Color color, byte[][] bytes) throws InterruptedException {
        for(int y = 0; y < bytes.length; y++) {
            for(int x = 0; x < bytes[y].length; x++) {
                if(bytes[y][x] == 1) {
                    canvas.setPixel(x + xOffset, y + yOffset, color.getRed(), color.getGreen(), color.getBlue());
                    Thread.sleep(50);
                    canvas = swapAndCopy(rgbMatrix, canvas);
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

    public static void fillMatrixWithVSync(RGBMatrixJNI rgbMatrix, List<Color> colors) throws InterruptedException {
        FrameCanvasJNI drawCanvas = rgbMatrix.createFrameCanvas();
        for(Color color : colors) {
            drawCanvas.fill(color.getRed(), color.getGreen(), color.getBlue());
            drawCanvas = rgbMatrix.swapOnVSync(drawCanvas);
            Thread.sleep(500);
        }
    }

    public static FrameCanvasJNI swapAndCopy(RGBMatrixJNI rgbMatrix, FrameCanvasJNI newCanvas) {
        FrameCanvasJNI oldCanvas = rgbMatrix.swapOnVSync(newCanvas);
        oldCanvas.copyFrom(newCanvas);

        return oldCanvas;
    }

    public static Color[] createColorArray(Color color, int size) {
        Color[] colors = new Color[size];
        Arrays.fill(colors, color);

        return colors;
    }

    public static Color[] createColorGradientArray(Color startColor, Color endColor, int size) {
        Color[] colors = new Color[size];
        float rStep = (endColor.getRed() - startColor.getRed()) / (float) size;
        float gStep = (endColor.getGreen() - startColor.getGreen()) / (float) size;
        float bStep = (endColor.getBlue() - startColor.getBlue()) / (float) size;

        for (int i = 0; i < size; i++) {
            int r = Math.min(255, Math.max(0, startColor.getRed() + Math.round(rStep * i)));
            int g = Math.min(255, Math.max(0, startColor.getGreen() + Math.round(gStep * i)));
            int b = Math.min(255, Math.max(0, startColor.getBlue() + Math.round(bStep * i)));
            colors[i] = new Color(r, g, b);
        }

        return colors;
    }

    public static void printColorGradients(RGBMatrixJNI rgbMatrix, Color[] gradients) throws InterruptedException {
        FrameCanvasJNI canvas = rgbMatrix.createFrameCanvas();

        int gradient = 0;
        for(int y = 0; y < 64; y += 4) {
            for(int x = 0; x < 64; x += 4) {
                canvas.setPixels(x, y, 4, 4, createColorArray(gradients[gradient], 16));
                gradient++;
            }
        }

        rgbMatrix.swapOnVSync(canvas);
    }

    private static byte[][] createHelloArray() {
        byte[][] helloArray = new byte[5][19];
        helloArray[0] = new byte[]{1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
        helloArray[1] = new byte[]{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloArray[2] = new byte[]{1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloArray[3] = new byte[]{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1};
        helloArray[4] = new byte[]{1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0};

        return helloArray;
    }

    private static byte[][] createWorldArray() {
        byte[][] worldArray = new byte[5][23];
        worldArray[0] = new byte[]{1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1};
        worldArray[1] = new byte[]{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        worldArray[2] = new byte[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1};
        worldArray[3] = new byte[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0};
        worldArray[4] = new byte[]{0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1};

        return worldArray;
    }
}
