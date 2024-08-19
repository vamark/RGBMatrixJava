#ifndef COLOR_CONVERTER_H
#define COLOR_CONVERTER_H

#include <jni.h>
#include "graphics.h"

class ColorConverter {
private:
    JNIEnv *env;
    jclass colorClass;

public:
    ColorConverter(JNIEnv *env);
    rgb_matrix::Color convert(jobject jColor);
};

#endif