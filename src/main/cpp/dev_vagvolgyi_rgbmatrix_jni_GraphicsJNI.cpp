#include "dev_vagvolgyi_rgbmatrix_jni_GraphicsJNI.h"
#include "led-matrix.h"
#include "graphics.h"
#include "converter/color_converter.h"
#include "bridge/font_bridge.h"
#include "bridge/frame_canvas_bridge.h"

using rgb_matrix::RGBMatrix;
using rgb_matrix::Color;
using rgb_matrix::Font;
using rgb_matrix::FrameCanvas;

JNIEXPORT jboolean JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_GraphicsJNI_setImage(JNIEnv* env, jclass,
jobject canvasJNI, jint canvasOffsetX, jint canvasOffsetY, jbyteArray imageBuffer, jlong imageBufferSize,
jint imageWidth, jint imageHeight, jboolean isBgr) {
    FrameCanvas* canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    jbyte* imageBufferPtr = env->GetByteArrayElements(imageBuffer, 0);
    bool result = rgb_matrix::SetImage(canvas, canvasOffsetX, canvasOffsetY,
        reinterpret_cast<unsigned char*>(imageBufferPtr), imageBufferSize, imageWidth, imageHeight, isBgr);
    env->ReleaseByteArrayElements(imageBuffer, imageBufferPtr, 0);
    return result;
}

void prepareTextDrawing(JNIEnv* env, jobject canvasJNI, jobject fontJNI, jobject colorJNI, jobject backgroundColorJNI,
jstring text, FrameCanvas*& canvas, Font*& font, Color& color, Color*& backgroundColor, const char*& textChars) {
    canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    font = FontBridge(env).toNative(fontJNI);
    ColorConverter colorConverter(env);
    color = *colorConverter.convert(colorJNI);
    backgroundColor = colorConverter.convert(backgroundColorJNI);
    textChars = env->GetStringUTFChars(text, 0);
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawText(JNIEnv* env, jclass, jobject canvasJNI,
jobject fontJNI, jint x, jint y, jobject colorJNI, jobject backgroundColorJNI, jstring text, jint kerningOffset) {
    FrameCanvas* canvas;
    Font* font;
    Color color;
    Color* backgroundColor;
    const char* textChars;

    prepareTextDrawing(env, canvasJNI, fontJNI, colorJNI, backgroundColorJNI, text, canvas, font, color,
        backgroundColor, textChars);

    jint result = rgb_matrix::DrawText(canvas, *font, x, y, color, backgroundColor, textChars, kerningOffset);

    env->ReleaseStringUTFChars(text, textChars);
    return result;
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_GraphicsJNI_verticalDrawText(JNIEnv* env, jclass, jobject
canvasJNI, jobject fontJNI, jint x, jint y, jobject colorJNI, jobject backgroundColorJNI, jstring text,
jint kerningOffset) {
    FrameCanvas* canvas;
    Font* font;
    Color color;
    Color* backgroundColor;
    const char* textChars;

    prepareTextDrawing(env, canvasJNI, fontJNI, colorJNI, backgroundColorJNI, text, canvas, font, color,
        backgroundColor, textChars);

    jint result = rgb_matrix::VerticalDrawText(canvas, *font, x, y, color, backgroundColor, textChars, kerningOffset);

    env->ReleaseStringUTFChars(text, textChars);
    return result;
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawCircle(JNIEnv* env, jclass, jobject canvasJNI,
jint x, jint y, jint radius, jobject colorJNI) {
    FrameCanvas* canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    Color color = *ColorConverter(env).convert(colorJNI);
    rgb_matrix::DrawCircle(canvas, x, y, radius, color);
}

JNIEXPORT void JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawLine(JNIEnv* env, jclass, jobject canvasJNI,
jint x1, jint y1, jint x2, jint y2, jobject colorJNI) {
    FrameCanvas* canvas = FrameCanvasBridge(env).toNative(canvasJNI);
    Color color = *ColorConverter(env).convert(colorJNI);
    rgb_matrix::DrawLine(canvas, x1, y1, x2, y2, color);
}