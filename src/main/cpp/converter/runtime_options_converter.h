#ifndef RUNTIME_OPTIONS_CONVERTER_H
#define RUNTIME_OPTIONS_CONVERTER_H

#include <jni.h>
#include "led-matrix.h"
#include "field_reader.h"
#include <string>

class RuntimeOptionsConverter {
private:
    JNIEnv *env;
    std::string dropPrivUser;
    std::string dropPrivGroup;

public:
    RuntimeOptionsConverter(JNIEnv *env);
    rgb_matrix::RuntimeOptions convert(jobject runtimeOptions);
};

#endif