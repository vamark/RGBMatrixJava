#include "font_bridge.h"

jobject FontBridge::toJava(Font* font) {
    return Bridge::toJava(font, "dev/vagvolgyi/rgbmatrix/jni/FontJNI", "(J)V");
}