#include "dev_vagvolgyi_rgbmatrix_jni_FontJNI.h"
#include "graphics.h"
#include "bridge/font_bridge.h"
#include <string>

using rgb_matrix::Font;

uint32_t toUnicodeCodepoint(JNIEnv *env, jstring jstr);

JNIEXPORT jlong JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FontJNI_loadFont(JNIEnv *env, jclass, jstring fontPath) {
    const char *nativeString = env->GetStringUTFChars(fontPath, 0);
    std::string fontPathStr(nativeString);
    env->ReleaseStringUTFChars(fontPath, nativeString);

    Font *font = new Font();
    if (!font->LoadFont(fontPathStr.c_str())) {
        delete font;
        return 0;
    }

    return reinterpret_cast<jlong>(font);
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FontJNI_getHeight(JNIEnv *env, jobject fontJNI) {
    return FontBridge(env).toNative(fontJNI)->height();
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FontJNI_getBaseline(JNIEnv *env, jobject fontJNI) {
    return FontBridge(env).toNative(fontJNI)->baseline();
}

JNIEXPORT jint JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FontJNI_getCharacterWidth(JNIEnv *env, jobject fontJNI, jstring
character) {
    return FontBridge(env).toNative(fontJNI)->CharacterWidth(toUnicodeCodepoint(env, character));
}

JNIEXPORT jobject JNICALL Java_dev_vagvolgyi_rgbmatrix_jni_FontJNI_createOutlineFont(JNIEnv *env, jobject fontJNI) {
    FontBridge fontBridge(env);
    Font *font = fontBridge.toNative(fontJNI);

    Font *outlineFont = font->CreateOutlineFont();

    return fontBridge.toJava(outlineFont);
}

uint32_t toUnicodeCodepoint(JNIEnv *env, jstring jstr) {
    const jchar *utf16 = env->GetStringChars(jstr, nullptr);
    jsize length = env->GetStringLength(jstr);

    uint32_t codepoint = 0;
    if (length == 1) {
        codepoint = utf16[0];
    }
    else if (length == 2) {
        codepoint = ((utf16[0] - 0xD800) << 10) + (utf16[1] - 0xDC00) + 0x10000;
    }

    env->ReleaseStringChars(jstr, utf16);
    return codepoint;
}