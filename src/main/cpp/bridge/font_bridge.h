#ifndef FONT_BRIDGE_H
#define FONT_BRIDGE_H

#include <jni.h>
#include "graphics.h"
#include "bridge.h"

using rgb_matrix::Font;

class FontBridge : public Bridge<Font> {
public:
    FontBridge(JNIEnv* env) : Bridge(env) {}
    jobject toJava(Font* font);
};

#endif