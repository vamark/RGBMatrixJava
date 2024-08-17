#include "io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI.h"
#include <iostream>

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_initMatrix(JNIEnv *, jclass) {
    std::cout << "Matrix initialized" << std::endl;
}

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_destroyMatrix(JNIEnv *, jclass) {
    std::cout << "Matrix destroyed" << std::endl;
}

void JNICALL Java_io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI_setPixel(JNIEnv *, jclass, jint row, jint col, jint r, jint g, jint b) {
    std::cout << "Set pixel at (" << row << ", " << col << ") to color (" << r << ", " << g << ", " << b << ")" << std::endl;
}