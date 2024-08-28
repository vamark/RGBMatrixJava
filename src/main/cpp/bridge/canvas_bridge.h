#ifndef CANVAS_BRIDGE_H
#define CANVAS_BRIDGE_H

#include <jni.h>
#include "led-matrix.h"
#include "bridge.h"

using rgb_matrix::Canvas;

class CanvasBridge : public Bridge<Canvas> {
private:
    JNIEnv* env;

public:
    CanvasBridge(JNIEnv* env) : Bridge(env) {}
    jobject toJava(Canvas* canvas);
};

#endif