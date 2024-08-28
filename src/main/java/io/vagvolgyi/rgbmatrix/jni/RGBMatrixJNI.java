package io.vagvolgyi.rgbmatrix.jni;

import io.vagvolgyi.rgbmatrix.jni.model.Options;
import io.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

public class RGBMatrixJNI extends CanvasJNI implements AutoCloseable {
    public RGBMatrixJNI(Options options, RuntimeOptions runtimeOptions) {
        super(createFromOptions(options, runtimeOptions));
    }

    private static native long createFromOptions(Options options, RuntimeOptions runtimeOptions);

    public native FrameCanvasJNI createFrameCanvas();

    public native FrameCanvasJNI swapOnVSync(FrameCanvasJNI canvas, int framerateFraction);

    public FrameCanvasJNI swapOnVSync(FrameCanvasJNI canvas) {
        return swapOnVSync(canvas, 1);
    }

    public native void setBrightness(int brightness);

    public native int getBrightness();

    public native void startRefresh();

    @Override
    public native void close();
}
