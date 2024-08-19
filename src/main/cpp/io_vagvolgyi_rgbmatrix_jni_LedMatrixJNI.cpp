#include "io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI.h"
#include "led-matrix.h"
#include "converter/options_converter.h"
#include "converter/runtime_options_converter.h"

using rgb_matrix::RGBMatrix;

RGBMatrix *matrix = nullptr;

void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_initMatrix(JNIEnv *env, jclass, jobject options, jobject runtimeOptions) {
    OptionsConverter optionsConverter(env);
    RuntimeOptionsConverter runtimeOptionsConverter(env);

    rgb_matrix::RGBMatrix::Options my_defaults = optionsConverter.convert(options);
    rgb_matrix::RuntimeOptions runtime_defaults = runtimeOptionsConverter.convert(runtimeOptions);

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

void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_setPixel(JNIEnv *, jclass, jint row, jint col, jint r, jint g, jint b) {
    if (matrix != nullptr) {
        matrix->SetPixel(row, col, r, g, b);
    }
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_fill(JNIEnv *, jclass, jint r, jint g, jint b) {
    if (matrix != nullptr) {
        matrix->Fill(r, g, b);
    }
}

JNIEXPORT void JNICALL Java_io_vagvolgyi_rgbmatrix_jni_LedMatrixJNI_clear(JNIEnv *, jclass) {
    if (matrix != nullptr) {
        matrix->Clear();
    }
}