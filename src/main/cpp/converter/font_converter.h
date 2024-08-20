#ifndef FONT_CONVERTER_H
#define FONT_CONVERTER_H

#include <jni.h>
#include "graphics.h"

using rgb_matrix::Font;

class FontConverter {
private:
    JNIEnv* env;

public:
    FontConverter(JNIEnv* env);
    Font* toNative(jobject fontJNI);
    jobject toJava(Font* font);
};

#endif