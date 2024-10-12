#include "dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI.h"
#include "converter/color_converter.h"
#include "bridge/frame_canvas_bridge.h"

using rgb_matrix::Color;

JNIEXPORT jboolean JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_setPWMBits(JNIEnv* env,
jobject frameCanvasJNI, jint pwmBits) {
    return FrameCanvasBridge(env).toNative(frameCanvasJNI)->SetPWMBits(pwmBits);
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_getPWMBits(JNIEnv* env, jobject frameCanvasJNI) {
    return FrameCanvasBridge(env).toNative(frameCanvasJNI)->pwmbits();
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_setLuminanceCorrect(JNIEnv* env,
jobject frameCanvasJNI, jboolean luminanceCorrect) {
    FrameCanvasBridge(env).toNative(frameCanvasJNI)->set_luminance_correct(luminanceCorrect);
}

JNIEXPORT jboolean JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_isLuminanceCorrect(JNIEnv* env,
jobject frameCanvasJNI) {
    return FrameCanvasBridge(env).toNative(frameCanvasJNI)->luminance_correct();
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_setBrightness(JNIEnv* env,
jobject frameCanvasJNI, jint brightness) {
    FrameCanvasBridge(env).toNative(frameCanvasJNI)->SetBrightness(brightness);
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_getBrightness(JNIEnv* env,
jobject frameCanvasJNI) {
    return FrameCanvasBridge(env).toNative(frameCanvasJNI)->brightness();
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_copyFrom(JNIEnv* env, jobject frameCanvasJNI,
jobject frameCanvasJNISource) {
    FrameCanvasBridge frameCanvasBridge(env);
    rgb_matrix::FrameCanvas* nativeFrameCanvas = frameCanvasBridge.toNative(frameCanvasJNI);
    rgb_matrix::FrameCanvas* nativeFrameCanvasSource = frameCanvasBridge.toNative(frameCanvasJNISource);
    nativeFrameCanvas->CopyFrom(*nativeFrameCanvasSource);
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FrameCanvasJNI_setPixels(JNIEnv* env, jobject frameCanvasJNI,
jint x, jint y, jint width, jint height, jobjectArray colorJNIs) {
    jsize length = env->GetArrayLength(colorJNIs);
    Color* colors = new Color[length];
    ColorConverter colorConverter(env);

    for (jsize i = 0; i < length; ++i) {
        jobject javaColor = env->GetObjectArrayElement(colorJNIs, i);
        colors[i] = *colorConverter.convert(javaColor);
        env->DeleteLocalRef(javaColor);
    }

    FrameCanvasBridge(env).toNative(frameCanvasJNI)->SetPixels(x, y, width, height, colors);

    delete[] colors;
}