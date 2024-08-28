#ifndef BRIDGE_H
#define BRIDGE_H

#include <jni.h>

template <typename T>
class Bridge {
protected:
    JNIEnv* env;

public:
    Bridge(JNIEnv* env) {
        this->env = env;
    }

    T* toNative(jobject jniObject) {
        jclass jniClass = env->GetObjectClass(jniObject);
        jfieldID nativePointerField = env->GetFieldID(jniClass, "nativePointer", "J");
        jlong nativePointer = env->GetLongField(jniObject, nativePointerField);

        return reinterpret_cast<T*>(nativePointer);
    }

    jobject toJava(T* nativeObject, const char* className, const char* constructorSignature) {
        jclass jniClass = env->FindClass(className);
        jmethodID constructor = env->GetMethodID(jniClass, "<init>", constructorSignature);

        return env->NewObject(jniClass, constructor, reinterpret_cast<jlong>(nativeObject));
    }
};

#endif