#include "frame_canvas_bridge.h"

jobject FrameCanvasBridge::toJava(FrameCanvas* frameCanvas) {
    return Bridge::toJava(frameCanvas, "dev/vagvolgyi/rgbmatrix/jni/FrameCanvasJNI", "(J)V");
}