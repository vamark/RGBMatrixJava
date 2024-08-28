#include "font_bridge.h"

jobject FontBridge::toJava(Font* font) {
    return Bridge::toJava(font, "io/vagvolgyi/rgbmatrix/jni/FontJNI", "(J)V");
}