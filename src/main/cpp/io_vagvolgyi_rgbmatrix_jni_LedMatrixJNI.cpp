#include "io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI.h"
#include "led-matrix.h"
#include "graphics.h"
#include "converter/options_converter.h"
#include "converter/runtime_options_converter.h"
#include "converter/color_converter.h"

using rgb_matrix::RGBMatrix;
using rgb_matrix::Color;

RGBMatrix *matrix = nullptr;

void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_initMatrix(JNIEnv *env, jclass, jobject options, jobject runtimeOptions) {
    if (matrix != nullptr) {
        delete matrix;
        matrix = nullptr;
    }

    rgb_matrix::RGBMatrix::Options my_defaults = OptionsConverter(env).toNative(options);
    rgb_matrix::RuntimeOptions runtime_defaults = RuntimeOptionsConverter(env).toNative(runtimeOptions);

    matrix = RGBMatrix::CreateFromOptions(my_defaults, runtime_defaults);
}

void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_destroyMatrix(JNIEnv *, jclass) {
    if (matrix != nullptr) {
        delete matrix;
        matrix = nullptr;
    }
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_setBrightness(JNIEnv *, jclass, jint brightness) {
    if (matrix != nullptr) {
        matrix->SetBrightness(brightness);
    }
}

void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_setPixel(JNIEnv *env, jclass, jint x, jint y, jobject jColor) {
    if (matrix != nullptr) {
        Color color = ColorConverter(env).toNative(jColor);
        matrix->SetPixel(x, y, color.r, color.g, color.b);
    }
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_fill(JNIEnv *env, jclass, jobject jColor) {
    if (matrix != nullptr) {
        Color color = ColorConverter(env).toNative(jColor);
        matrix->Fill(color.r, color.g, color.b);
    }
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_clear(JNIEnv *, jclass) {
    if (matrix != nullptr) {
        matrix->Clear();
    }
}