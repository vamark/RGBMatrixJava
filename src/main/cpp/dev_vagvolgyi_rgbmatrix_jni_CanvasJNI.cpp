#include "dev_vagvolgyi_rgbmatrix_jni_CanvasJNI.h"
#include "bridge/canvas_bridge.h"

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_CanvasJNI_getWidth(JNIEnv* env, jobject canvasJNI) {
    return CanvasBridge(env).toNative(canvasJNI)->width();
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_CanvasJNI_getHeight(JNIEnv* env, jobject canvasJNI) {
    return CanvasBridge(env).toNative(canvasJNI)->height();
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_CanvasJNI_setPixel(JNIEnv* env, jobject canvasJNI, jint x,
jint y, jint r, jint g, jint b) {
    CanvasBridge(env).toNative(canvasJNI)->SetPixel(x, y, r, g, b);
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_CanvasJNI_fill(JNIEnv* env, jobject canvasJNI, jint r, jint g,
jint b) {
    CanvasBridge(env).toNative(canvasJNI)->Fill(r, g, b);
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_CanvasJNI_clear(JNIEnv* env, jobject canvasJNI) {
    CanvasBridge(env).toNative(canvasJNI)->Clear();
}