package io.vagvolgyi.rgbmatrix.jni.api;

public interface Canvas {
    int getWidth();

    int getHeight();

    void setPixel(int x, int y, int r, int g, int b);

    void fill(int r, int g, int b);

    void clear();
}
