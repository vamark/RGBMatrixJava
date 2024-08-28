#include "io_vagvolgyi_rgbmatrix_jni_GraphicsJNI.h"
#include "led-matrix.h"
#include "graphics.h"
#include "converter/color_converter.h"
#include "bridge/font_bridge.h"
#include "bridge/frame_canvas_bridge.h"

using rgb_matrix::RGBMatrix;
using rgb_matrix::Color;
using rgb_matrix::Font;
using rgb_matrix::FrameCanvas;

JNIEXPORT jint JNICALL Java_io_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawText(JNIEnv* env, jclass, jobject canvasJNI, jobject fontJNI, jint x, jint y, jobject colorJNI, jstring text) {
    FrameCanvas* canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    Font* font = FontBridge(env).toNative(fontJNI);
    Color color = ColorConverter(env).convert(colorJNI);
    const char* textChars = env->GetStringUTFChars(text, 0);

    jint result = rgb_matrix::DrawText(canvas, *font, x, y, color, textChars);

    env->ReleaseStringUTFChars(text, textChars);
    return result;
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawCircle(JNIEnv *env, jclass, jobject canvasJNI, jint x, jint y, jint radius, jobject colorJNI) {
    FrameCanvas* canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    Color color = ColorConverter(env).convert(colorJNI);
    rgb_matrix::DrawCircle(canvas, x, y, radius, color);
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawLine(JNIEnv *env, jclass, jobject canvasJNI, jint x1, jint y1, jint x2, jint y2, jobject colorJNI) {
    FrameCanvas* canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    Color color = ColorConverter(env).convert(colorJNI);
    rgb_matrix::DrawLine(canvas, x1, y1, x2, y2, color);
}