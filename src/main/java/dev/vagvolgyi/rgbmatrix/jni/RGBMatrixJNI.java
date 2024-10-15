package dev.vagvolgyi.rgbmatrix.jni;

import dev.vagvolgyi.rgbmatrix.jni.model.Options;
import dev.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;
import dev.vagvolgyi.rgbmatrix.jni.api.FrameCanvas;
import dev.vagvolgyi.rgbmatrix.jni.api.Matrix;
import dev.vagvolgyi.rgbmatrix.util.LibLoader;

public class RGBMatrixJNI extends CanvasJNI implements Matrix {
    static {
        LibLoader.loadJniLib();
    }

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
