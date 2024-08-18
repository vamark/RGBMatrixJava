#include "io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI.h"
#include "led-matrix.h"

using rgb_matrix::RGBMatrix;

RGBMatrix *matrix = nullptr;

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_initMatrix(JNIEnv *, jclass) {
    RGBMatrix::Options my_defaults;
    my_defaults.hardware_mapping = "adafruit-hat-pwm";
    my_defaults.chain_length = 1;
    my_defaults.cols = 64;
    my_defaults.rows = 64;
    my_defaults.led_rgb_sequence = "BGR";

    rgb_matrix::RuntimeOptions runtime_defaults;
    runtime_defaults.drop_privileges = 1;

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