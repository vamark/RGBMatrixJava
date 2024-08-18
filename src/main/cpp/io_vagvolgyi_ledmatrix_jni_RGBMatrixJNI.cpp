#include "io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI.h"
#include "led-matrix.h"
#include "converter/options_converter.h"
#include "converter/runtime_options_converter.h"

using rgb_matrix::RGBMatrix;

RGBMatrix *matrix = nullptr;

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_initMatrix(JNIEnv *env, jclass, jobject options, jobject runtimeOptions) {
    OptionsConverter optionsConverter(env);
    RuntimeOptionsConverter runtimeOptionsConverter(env);

    rgb_matrix::RGBMatrix::Options my_defaults = optionsConverter.convert(options);
    rgb_matrix::RuntimeOptions runtime_defaults = runtimeOptionsConverter.convert(runtimeOptions);

    matrix = RGBMatrix::CreateFromOptions(my_defaults, runtime_defaults);
}

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_destroyMatrix(JNIEnv *, jclass) {
    if (matrix != nullptr) {
        delete matrix;
        matrix = nullptr;
    }
}

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_setPixel(JNIEnv *, jclass, jint row, jint col, jint r, jint g, jint b) {
    if (matrix != nullptr) {
        matrix->SetPixel(row, col, r, g, b);
    }
}