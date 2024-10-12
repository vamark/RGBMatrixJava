package dev.vagvolgyi.rgbmatrix.wrapper;

import dev.vagvolgyi.rgbmatrix.jni.RGBMatrixJNI;
import dev.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;
import dev.vagvolgyi.rgbmatrix.jni.api.Matrix;
import dev.vagvolgyi.rgbmatrix.jni.model.Options;
import dev.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * A renderer for an RGB matrix that uses a buffered approach.
 * This class provides various methods to draw shapes, text, and images into a buffer.
 * The buffer is then displayed on the matrix by using the 'commit()' method.
 *
 * <p>This class is designed to work with the RGBMatrixJNI library, which provides
 * native access to the RGB matrix hardware. The renderer uses a double-buffering
 * technique to minimize flickering and improve rendering performance.</p>
 */
public class BufferedMatrixRenderer implements AutoCloseable {
    private final Matrix matrix;
    private final Graphics graphics;

    private FrameCanvas buffer;

    static {
        try {
            System.loadLibrary("rgbmatrixjni");
        }
        catch (UnsatisfiedLinkError e) {
            Logger.getLogger(BufferedMatrixRenderer.class.getName()).severe("Unable to load the rgbmatrixjni library");
        }
    }

    /**
     * Constructs a BufferedMatrixRenderer and initializes the matrix with configuration from the matrix_config.properties
     * file. It also initializes the buffer for drawing.
     *
     * @throws IOException if the properties file cannot be found or loaded.
     */
    public BufferedMatrixRenderer() throws IOException {
        Properties properties = new Properties();
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("matrix_config.properties")) {
            if(input == null) {
                throw new IOException("Unable to find matrix_config.properties");
            }
            properties.load(input);
        }
        MatrixConfigParser parser = new MatrixConfigParser(properties);
        matrix = createMatrix(parser.parseOptions(), parser.parseRuntimeOptions());
        buffer = matrix.createFrameCanvas();
        graphics = createGraphics();
    }

    /**
     * Sets the brightness. Only values between 0 and 100 are valid.
     * The brightness will only be applied for the pixels drawn after this method is called.
     *
     * @param brightness the brightness level to set.
     * @throws IllegalArgumentException if the brightness is not between 0 and 100.
     */
    public void setBrightness(int brightness) {
        if(brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness must be between 0 and 100");
        }

        buffer.setBrightness(brightness);
    }

    /**
     * Draws a single pixel on the buffer.
     *
     * @param point the location of the pixel.
     * @param color the color of the pixel.
     */
    public void drawPixel(Point point, Color color) {
        buffer.setPixel(point.x, point.y, color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Draws multiple pixels on the buffer.
     *
     * @param start  the starting location of the pixels.
     * @param width  the width of the pixel area.
     * @param height the height of the pixel area.
     * @param colors the colors of the pixels, in row-major order.
     */
    public void drawPixels(Point start, int width, int height, Collection<Color> colors) {
        if (colors.size() != width * height) {
            throw new IllegalArgumentException("The number of colors must match the width and height");
        }

        buffer.setPixels(start.x, start.y, width, height, colors.toArray(new Color[0]));
    }

    /**
     * Fills the entire buffer with a single color.
     *
     * @param color the color to fill the matrix with.
     */
    public void fill(Color color) {
        buffer.fill(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Draws a line on the buffer.
     *
     * @param start the starting point of the line.
     * @param end   the ending point of the line.
     * @param color the color of the line.
     */
    public void drawLine(Point start, Point end, Color color) {
        graphics.drawLine(buffer, start.x, start.y, end.x, end.y, color);
    }

    /**
     * Draws a circle on the buffer.
     *
     * @param center the center of the circle.
     * @param radius the radius of the circle.
     * @param color  the color of the circle.
     */
    public void drawCircle(Point center, int radius, Color color) {
        graphics.drawCircle(buffer, center.x, center.y, radius, color);
    }

    /**
     * Draws text on the buffer.
     *
     * @param font  the BDF font to use for the text.
     * @param start the starting point of the text.
     * @param color the color of the text.
     * @param text  the text to draw.
     * @return the width of the text in pixels.
     */
    public int drawText(BdfFont font, Point start, Color color, String text) {
        return graphics.drawText(buffer, font.getFont(), start.x, start.y, color, text);
    }

    /**
     * Draws text on the buffer vertically.
     *
     * @param font  the BDF font to use for the text.
     * @param start the starting point of the text.
     * @param color the color of the text.
     * @param text  the text to draw.
     * @return the height of the text in pixels.
     */
    public int drawVerticalText(BdfFont font, Point start, Color color, String text) {
        return graphics.verticalDrawText(buffer, font.getFont(), start.x, start.y, color, text);
    }

    /**
     * Draws an image on the buffer.
     *
     * @param start the starting point of the image.
     * @param image the image to draw.
     * @return true if the image was drawn successfully, false otherwise.
     */
    public boolean drawImage(Point start, BufferedImage image) {
        byte[] imageBuffer = new byte[image.getWidth() * image.getHeight() * 3];
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int index = (y * image.getWidth() + x) * 3;
                imageBuffer[index] = (byte) color.getRed();
                imageBuffer[index + 1] = (byte) color.getGreen();
                imageBuffer[index + 2] = (byte) color.getBlue();
            }
        }

        return graphics.setImage(buffer, start.x, start.y, imageBuffer, imageBuffer.length, image.getWidth(),
                                    image.getHeight(), false);
    }

    /**
     * Clears the buffer.
     */
    public void clear() {
        buffer.clear();
    }

    /**
     * Commits the current buffer to the matrix and prepares a new buffer.
     */
    public void commit() {
        buffer = matrix.swapOnVSync(buffer);
        buffer.clear();
    }

    /**
     * Closes the matrix and releases any resources held by it.
     */
    @Override
    public void close() throws Exception {
        matrix.close();
    }

    Matrix createMatrix(Options options, RuntimeOptions runtimeOptions) {
        return new RGBMatrixJNI(options, runtimeOptions);
    }

    Graphics createGraphics() {
        return new Graphics();
    }
}
