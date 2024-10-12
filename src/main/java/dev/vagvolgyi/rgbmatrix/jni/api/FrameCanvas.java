package dev.vagvolgyi.rgbmatrix.jni.api;

import java.awt.*;

public interface FrameCanvas extends Canvas {
    boolean setPWMBits(int value);

    int getPWMBits();

    void setLuminanceCorrect(boolean on);

    boolean isLuminanceCorrect();

    void setBrightness(int brightness);

    int getBrightness();

    void copyFrom(FrameCanvas other);

    void setPixels(int x, int y, int width, int height, Color[] colors);
}
