package io.vagvolgyi.rgbmatrix.jni.api;

public interface Matrix extends AutoCloseable, Canvas {
    FrameCanvas createFrameCanvas();

    FrameCanvas swapOnVSync(FrameCanvas canvas, int framerateFraction);

    FrameCanvas swapOnVSync(FrameCanvas canvas);

    void setBrightness(int brightness);

    int getBrightness();

    void startRefresh();
}
