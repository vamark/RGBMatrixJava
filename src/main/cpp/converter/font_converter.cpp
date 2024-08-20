#include "font_converter.h"

FontConverter::FontConverter(JNIEnv* env) {
    this->env = env;
}

Font* FontConverter::toNative(jobject fontJNI) {
    jclass fontJNIClass = env->GetObjectClass(fontJNI);
    jfieldID fontPointerField = env->GetFieldID(fontJNIClass, "nativePointer", "J");
    jlong fontPointer = env->GetLongField(fontJNI, fontPointerField);
    return reinterpret_cast<Font*>(fontPointer);
}

jobject FontConverter::toJava(Font* font) {
    jclass fontJNIClass = env->FindClass("io/vagvolgyi/rgbmatrix/jni/FontJNI");
    jmethodID constructor = env->GetMethodID(fontJNIClass, "<init>", "(J)V");
    return env->NewObject(fontJNIClass, constructor, reinterpret_cast<jlong>(font));
}