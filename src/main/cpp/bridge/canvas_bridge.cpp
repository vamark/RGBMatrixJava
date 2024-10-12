#include "canvas_bridge.h"

jobject CanvasBridge::toJava(Canvas* canvas) {
    return Bridge::toJava(canvas, "dev/vagvolgyi/rgbmatrix/jni/CanvasJNI", "(J)V");
}