#include "io_vagvolgyi_rgbmatrix_jni_FontJNI.h"
#include "graphics.h"
#include "converter/font_converter.h"
#include <string>

using rgb_matrix::Font;

uint32_t jstringToUnicodeCodepoint(JNIEnv *env, jstring jstr);

JNIEXPORT jlong JNICALL Java_io_vagvolgyi_rgbmatrix_jni_FontJNI_loadFont(JNIEnv *env, jclass, jstring fontPath) {
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

JNIEXPORT jint JNICALL Java_io_vagvolgyi_rgbmatrix_jni_FontJNI_getHeight(JNIEnv *env, jobject fontJNI) {
    return FontConverter(env).toNative(fontJNI)->height();
}

JNIEXPORT jint JNICALL Java_io_vagvolgyi_rgbmatrix_jni_FontJNI_getBaseline(JNIEnv *env, jobject fontJNI) {
    return FontConverter(env).toNative(fontJNI)->baseline();
}

JNIEXPORT jint JNICALL Java_io_vagvolgyi_rgbmatrix_jni_FontJNI_getCharacterWidth(JNIEnv *env, jobject fontJNI, jstring character) {
    return FontConverter(env).toNative(fontJNI)->CharacterWidth(jstringToUnicodeCodepoint(env, character));
}

JNIEXPORT jobject JNICALL Java_io_vagvolgyi_rgbmatrix_jni_FontJNI_createOutlineFont(JNIEnv *env, jobject fontJNI) {
    FontConverter fontConverter(env);
    Font *font = fontConverter.toNative(fontJNI);

    Font *outlineFont = font->CreateOutlineFont();

    return fontConverter.toJava(outlineFont);
}

uint32_t jstringToUnicodeCodepoint(JNIEnv *env, jstring jstr) {
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