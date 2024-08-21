#include "io_vagvolgyi_rgbmatrix_jni_GraphicsJNI.h"
#include "led-matrix.h"
#include "graphics.h"
#include "converter/font_converter.h"
#include "converter/color_converter.h"

using rgb_matrix::RGBMatrix;
using rgb_matrix::Color;
using rgb_matrix::Font;

extern RGBMatrix *matrix;

JNIEXPORT jint JNICALL Java_io_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawText(JNIEnv *env, jclass, jobject fontJNI, jint x, jint y, jobject jColor, jstring text) {
    int result = 0;

    if (matrix != nullptr) {
        Font *font = FontConverter(env).toNative(fontJNI);

        if (font != nullptr) {
            Color color = ColorConverter(env).toNative(jColor);
            const char *textChars = env->GetStringUTFChars(text, 0);

            result = rgb_matrix::DrawText(matrix, *font, x, y, color, textChars);

            env->ReleaseStringUTFChars(text, textChars);
        }
    }

    return result;
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawCircle(JNIEnv *env, jclass, jint x, jint y, jint radius, jobject jColor) {
    if (matrix != nullptr) {
        Color color = ColorConverter(env).toNative(jColor);
        rgb_matrix::DrawCircle(matrix, x, y, radius, color);
    }
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_GraphicsJNI_drawLine(JNIEnv *env, jclass, jint x1, jint y1, jint x2, jint y2, jobject jColor) {
    if (matrix != nullptr) {
        Color color = ColorConverter(env).toNative(jColor);
        rgb_matrix::DrawLine(matrix, x1, y1, x2, y2, color);
    }
}