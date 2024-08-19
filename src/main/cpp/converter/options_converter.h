#ifndef OPTIONS_CONVERTER_H
#define OPTIONS_CONVERTER_H

#include <jni.h>
#include "led-matrix.h"
#include "field_reader.h"

class OptionsConverter {
private:
    JNIEnv *env;

public:
    OptionsConverter(JNIEnv *env);
    rgb_matrix::RGBMatrix::Options convert(jobject options);
};

#endif