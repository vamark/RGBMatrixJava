#include "io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI.h"
#include "led-matrix.h"
#include "graphics.h"
#include "converter/options_converter.h"
#include "converter/runtime_options_converter.h"
#include "bridge/rgb_matrix_bridge.h"
#include "bridge/frame_canvas_bridge.h"

using rgb_matrix::RGBMatrix;
using rgb_matrix::FrameCanvas;

JNIEXPORT jlong JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_createFromOptions(JNIEnv* env, jclass, jobject options, jobject runtimeOptions) {
    rgb_matrix::RGBMatrix::Options nativeOptions = OptionsConverter(env).convert(options);
    rgb_matrix::RuntimeOptions nativeRuntimeOptions = RuntimeOptionsConverter(env).convert(runtimeOptions);

    RGBMatrix* matrix = RGBMatrix::CreateFromOptions(nativeOptions, nativeRuntimeOptions);

    return reinterpret_cast<jlong>(matrix);
}

JNIEXPORT jobject JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_createFrameCanvas(JNIEnv* env, jobject matrixJNI) {
    FrameCanvas* frameCanvas = RGBMatrixBridge(env).toNative(matrixJNI)->CreateFrameCanvas();
    return FrameCanvasBridge(env).toJava(frameCanvas);
}

JNIEXPORT jobject JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_swapOnVSync(JNIEnv* env, jobject matrixJNI, jobject canvasJNI, jint framerateFraction) {
    FrameCanvasBridge frameCanvasBridge(env);
    FrameCanvas* frameCanvas = frameCanvasBridge.toNative(canvasJNI);
    FrameCanvas* oldFrameCanvas = RGBMatrixBridge(env).toNative(matrixJNI)->SwapOnVSync(frameCanvas, framerateFraction);

    return frameCanvasBridge.toJava(oldFrameCanvas);
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_setBrightness(JNIEnv* env, jobject matrixJNI, jint brightness) {
    RGBMatrixBridge(env).toNative(matrixJNI)->SetBrightness(brightness);
}

JNIEXPORT jint JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_getBrightness(JNIEnv* env, jobject matrixJNI) {
    return RGBMatrixBridge(env).toNative(matrixJNI)->brightness();
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_startRefresh(JNIEnv* env, jobject matrixJNI) {
    RGBMatrixBridge(env).toNative(matrixJNI)->StartRefresh();
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_RGBMatrixJNI_close(JNIEnv* env, jobject matrixJNI) {
    delete RGBMatrixBridge(env).toNative(matrixJNI);
}