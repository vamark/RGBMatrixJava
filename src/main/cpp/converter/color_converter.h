#ifndef COLOR_CONVERTER_H
#define COLOR_CONVERTER_H

#include <jni.h>
#include <stdexcept>

class ColorConverter {
private:
    JNIEnv *env;
    jclass colorClass;

public:
    ColorConverter(JNIEnv *env);
    rgb_matrix::Color toNative(jobject jColor);
};

#endif