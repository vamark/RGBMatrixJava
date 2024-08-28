#ifndef FRAME_CANVAS_BRIDGE_H
#define FRAME_CANVAS_BRIDGE_H

#include <jni.h>
#include "led-matrix.h"
#include "bridge.h"

using rgb_matrix::FrameCanvas;

class FrameCanvasBridge : public Bridge<FrameCanvas> {
private:
    JNIEnv* env;

public:
    FrameCanvasBridge(JNIEnv* env) : Bridge(env) {}
    jobject toJava(FrameCanvas* frameCanvas);
};

#endif