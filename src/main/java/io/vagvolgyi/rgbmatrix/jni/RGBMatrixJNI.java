package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;
import io.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;
import io.vagvolgyi.rgbmatrix.jni.api.Matrix;

public class RGBMatrixJNI extends CanvasJNI implements Matrix {
    public RGBMatrixJNI(Options options, RuntimeOptions runtimeOptions) {
        super(createFromOptions(options, runtimeOptions));
    }

    private static native long createFromOptions(Options options, RuntimeOptions runtimeOptions);

    @Override
    public native FrameCanvas createFrameCanvas();

    @Override
    public native FrameCanvas swapOnVSync(FrameCanvas canvas, int framerateFraction);

    @Override
    public FrameCanvas swapOnVSync(FrameCanvas canvas) {
        return swapOnVSync(canvas, 1);
    }

    @Override
    public native void setBrightness(int brightness);

    @Override
    public native int getBrightness();

    @Override
    public native void startRefresh();

    @Override
    public native void close();
}
