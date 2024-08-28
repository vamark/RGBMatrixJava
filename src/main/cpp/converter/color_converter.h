#ifndef COLOR_CONVERTER_H
#define COLOR_CONVERTER_H

#include <jni.h>
#include "led-matrix.h"

using rgb_matrix::Color;

class ColorConverter {
private:
    JNIEnv *env;
    jclass colorClass;

public:
    ColorConverter(JNIEnv *env);
    Color convert(jobject jColor);
};

#endif