#include "canvas_bridge.h"

jobject CanvasBridge::toJava(Canvas* canvas) {
    return Bridge::toJava(canvas, "io/vagvolgyi/rgbmatrix/jni/CanvasJNI", "(J)V");
}