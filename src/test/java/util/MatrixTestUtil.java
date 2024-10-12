package util;

import dev.vagvolgyi.rgbmatrix.jni.FontJNI;
import dev.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;
import dev.vagvolgyi.rgbmatrix.jni.api.Matrix;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.Color.*;
import static util.SleepUtil.sleepFor;

public class MatrixTestUtil {
    public static final byte[][] HELLO = createHelloArray();
    public static final byte[][] WORLD = createWorldArray();

    public static void printMatrix(Matrix matrix, int xOffset, int yOffset, Color color, byte[][] bytes) {
        for(int y = 0; y < bytes.length; y++) {
            for(int x = 0; x < bytes[y].length; x++) {
                if(bytes[y][x] == 1) {
                    matrix.setPixel(x + xOffset, y + yOffset, color.getRed(), color.getGreen(), color.getBlue());
                    sleepFor(50);
                }
            }
        }
    }

    public static void printMatrixWithVSync(Matrix rgbMatrix, FrameCanvas canvas, int xOffset, int yOffset, Color color, byte[][] bytes) {
        for(int y = 0; y < bytes.length; y++) {
            for(int x = 0; x < bytes[y].length; x++) {
                if(bytes[y][x] == 1) {
                    canvas.setPixel(x + xOffset, y + yOffset, color.getRed(), color.getGreen(), color.getBlue());
                    sleepFor(50);
                    canvas = swapAndCopy(rgbMatrix, canvas);
                }
            }
        }
    }

    public static void fillAndClearMatrix(Matrix matrix, List<Color> colors) {
        for(Color color : colors) {
            matrix.fill(color.getRed(), color.getGreen(), color.getBlue());
            sleepFor(300);
            matrix.clear();
            sleepFor(200);
        }
    }

    public static void fillMatrixWithVSync(Matrix rgbMatrix, List<Color> colors) {
        FrameCanvas drawCanvas = rgbMatrix.createFrameCanvas();
        for(Color color : colors) {
            drawCanvas.fill(color.getRed(), color.getGreen(), color.getBlue());
            drawCanvas = rgbMatrix.swapOnVSync(drawCanvas);
            sleepFor(500);
        }
    }

    public static FrameCanvas swapAndCopy(Matrix rgbMatrix, FrameCanvas newCanvas) {
        FrameCanvas oldCanvas = rgbMatrix.swapOnVSync(newCanvas);
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

    public static void printColorGradients(Matrix rgbMatrix, Color[] gradients) {
        FrameCanvas canvas = rgbMatrix.createFrameCanvas();

        int gradient = 0;
        for(int y = 0; y < 64; y += 4) {
            for(int x = 0; x < 64; x += 4) {
                canvas.setPixels(x, y, 4, 4, createColorArray(gradients[gradient], 16));
                gradient++;
            }
        }

        rgbMatrix.swapOnVSync(canvas);
    }

    public static FontJNI loadFont(String fontName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("fonts/" + fontName + ".bdf");
        if(resource == null) {
            throw new IllegalArgumentException("Font not found! " + fontName);
        }
        else {
            return new FontJNI(resource.getFile());
        }
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

    public static byte[] createImageArray() {
        Map<Byte, Color> colorMap = new HashMap<>();
        colorMap.put((byte) 0, BLUE);
        colorMap.put((byte) 1, ORANGE);
        colorMap.put((byte) 2, YELLOW);

        byte[][] imageArray = new byte[15][15];
        imageArray[0]  = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        imageArray[1]  = new byte[]{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0};
        imageArray[2]  = new byte[]{0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0};
        imageArray[3]  = new byte[]{0, 1, 0, 0, 1, 2, 1, 1, 1, 2, 1, 0, 0, 1, 0};
        imageArray[4]  = new byte[]{0, 0, 1, 1, 0, 1, 2, 2, 2, 1, 0, 1, 1, 0, 0};
        imageArray[5]  = new byte[]{0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0};
        imageArray[6]  = new byte[]{0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0};
        imageArray[7]  = new byte[]{0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 0};
        imageArray[8]  = new byte[]{0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0};
        imageArray[9]  = new byte[]{0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0};
        imageArray[10] = new byte[]{0, 0, 1, 1, 0, 1, 2, 2, 2, 1, 0, 1, 1, 0, 0};
        imageArray[11] = new byte[]{0, 1, 0, 0, 1, 2, 1, 1, 1, 2, 1, 0, 0, 1, 0};
        imageArray[12] = new byte[]{0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0};
        imageArray[13] = new byte[]{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0};
        imageArray[14] = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        byte[] imageBuffer = new byte[15 * 15 * 3];
        for(int y = 0; y < imageArray.length; y++) {
            for(int x = 0; x < imageArray[y].length; x++) {
                Color color = colorMap.get(imageArray[y][x]);
                int index = (y * 15 + x) * 3;
                imageBuffer[index] = (byte) color.getRed();
                imageBuffer[index + 1] = (byte) color.getGreen();
                imageBuffer[index + 2] = (byte) color.getBlue();
            }
        }

        return imageBuffer;
    }
}
