package dev.vagvolgyi.rgbmatrix.wrapper;

import dev.vagvolgyi.rgbmatrix.jni.FontJNI;
import dev.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;
import dev.vagvolgyi.rgbmatrix.jni.api.Matrix;
import dev.vagvolgyi.rgbmatrix.jni.model.Options;
import dev.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static java.awt.Color.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BufferedMatrixRendererTest {
    @Mock
    private Matrix mockMatrix;

    @Mock
    private FrameCanvas mockBuffer;

    @Mock
    private Graphics mockGraphics;

    private BufferedMatrixRenderer renderer;

    @BeforeEach
    void setUp() throws IOException {
        Mockito.doReturn(mockBuffer).when(mockMatrix).createFrameCanvas();
        when(mockMatrix.createFrameCanvas()).thenReturn(mockBuffer);

        renderer = new BufferedMatrixRenderer() {
            @Override
            Matrix createMatrix(Options options, RuntimeOptions runtimeOptions) {
                return mockMatrix;
            }

            @Override
            Graphics createGraphics() {
                return mockGraphics;
            }
        };
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 50, 100})
    void setBrightness_withValidBrightness_setsBrightnessCorrectly(int brightness) {
        renderer.setBrightness(brightness);

        verify(mockBuffer).setBrightness(brightness);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    void setBrightness_withInvalidBrightness_doesNotSetBrightness(int brightness) {
        assertThrows(IllegalArgumentException.class, () -> renderer.setBrightness(brightness));
        verify(mockBuffer, Mockito.never()).setBrightness(brightness);
    }

    @Test
    void drawPixel_setsPixelCorrectly() {
        Point point = new Point(1, 2);
        Color color = WHITE;

        renderer.drawPixel(point, color);

        verify(mockBuffer).setPixel(point.x, point.y, color.getRed(), color.getGreen(), color.getBlue());
    }

    @Test
    void drawPixels_withValidColorArray_setsPixelsCorrectly() {
        Point point = new Point(1, 2);
        List<Color> colors = asList(RED, GREEN, BLUE, BLACK);

        renderer.drawPixels(point, 2, 2, colors);

        verify(mockBuffer).setPixels(point.x, point.y, 2, 2, colors.toArray(new Color[0]));
    }

    @Test
    void drawPixels_withInvalidColorArray_throwsException() {
        Point point = new Point(1, 2);
        List<Color> colors = asList(RED, GREEN, BLUE);

        assertThrows(IllegalArgumentException.class, () -> renderer.drawPixels(point, 2, 2, colors));
        verify(mockBuffer, Mockito.never()).setPixels(point.x, point.y, 2, 2, colors.toArray(new Color[0]));
    }

    @Test
    void fill_fillsBufferCorrectly() {
        Color color = WHITE;

        renderer.fill(color);

        verify(mockBuffer).fill(color.getRed(), color.getGreen(), color.getBlue());
    }

    @Test
    void drawLine_drawsLineCorrectly() {
        Point start = new Point(1, 2);
        Point end = new Point(3, 4);
        Color color = WHITE;

        renderer.drawLine(start, end, color);

        verify(mockGraphics).drawLine(mockBuffer, start.x, start.y, end.x, end.y, color);
    }

    @Test
    void drawCircle_drawsCircleCorrectly() {
        Point center = new Point(1, 2);
        int radius = 3;
        Color color = WHITE;

        renderer.drawCircle(center, radius, color);

        verify(mockGraphics).drawCircle(mockBuffer, center.x, center.y, radius, color);
    }

    @Test
    void drawText_drawsTextCorrectly() {
        FontJNI mockFontJni = mock(FontJNI.class);
        BdfFont mockBdfFont = mock(BdfFont.class);
        when(mockBdfFont.getFont()).thenReturn(mockFontJni);

        Point point = new Point(1, 2);
        Color color = WHITE;
        String text = "Hello, World!";

        renderer.drawText(mockBdfFont, point, color, text);

        verify(mockGraphics).drawText(mockBuffer, mockFontJni, point.x, point.y, color, text);
    }

    @Test
    void drawVerticalText_drawsTextCorrectly() {
        FontJNI mockFontJni = mock(FontJNI.class);
        BdfFont mockBdfFont = mock(BdfFont.class);
        when(mockBdfFont.getFont()).thenReturn(mockFontJni);

        Point point = new Point(1, 2);
        Color color = WHITE;
        String text = "Hello, World!";

        renderer.drawVerticalText(mockBdfFont, point, color, text);

        verify(mockGraphics).verticalDrawText(mockBuffer, mockFontJni, point.x, point.y, color, text);
    }

    @Test
    void drawImage_drawsImageCorrectly() {
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(WHITE);
        graphics.fillRect(0, 0, 1, 2);
        graphics.setColor(BLACK);
        graphics.fillRect(1, 0, 1, 2);

        Point point = new Point(1, 2);

        renderer.drawImage(point, image);

        byte[] imageBuffer = new byte[2 * 2 * 3];
        imageBuffer[0] = (byte) WHITE.getRed();
        imageBuffer[1] = (byte) WHITE.getGreen();
        imageBuffer[2] = (byte) WHITE.getBlue();
        imageBuffer[3] = (byte) BLACK.getRed();
        imageBuffer[4] = (byte) BLACK.getGreen();
        imageBuffer[5] = (byte) BLACK.getBlue();
        imageBuffer[6] = (byte) WHITE.getRed();
        imageBuffer[7] = (byte) WHITE.getGreen();
        imageBuffer[8] = (byte) WHITE.getBlue();
        imageBuffer[9] = (byte) BLACK.getRed();
        imageBuffer[10] = (byte) BLACK.getGreen();
        imageBuffer[11] = (byte) BLACK.getBlue();
        verify(mockGraphics).setImage(mockBuffer, point.x, point.y, imageBuffer, imageBuffer.length, 2, 2, false);
    }

    @Test
    void clear_clearsBuffer() {
        renderer.clear();

        verify(mockBuffer).clear();
    }

    @Test
    void commit_frameSwappedAndBufferCleared() {
        FrameCanvas newBuffer = mock(FrameCanvas.class);
        when(mockMatrix.swapOnVSync(mockBuffer)).thenReturn(newBuffer);

        renderer.commit();

        verify(mockMatrix).swapOnVSync(mockBuffer);
        verify(newBuffer).clear();
    }

    @Test
    void close_closesMatrix() throws Exception {
        renderer.close();

        verify(mockMatrix).close();
    }
}