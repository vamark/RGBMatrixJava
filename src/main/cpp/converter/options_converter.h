#ifndef OPTIONS_CONVERTER_H
#define OPTIONS_CONVERTER_H

#include <jni.h>
#include "led-matrix.h"
#include "field_reader.h"
#include <string>

class OptionsConverter {
private:
    JNIEnv *env;
    std::string hardwareMapping;
    std::string ledRgbSequence;
    std::string pixelMapperConfig;
    std::string panelType;

public:
    OptionsConverter(JNIEnv *env);
    rgb_matrix::RGBMatrix::Options convert(jobject options);
};

#endif