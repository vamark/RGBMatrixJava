#ifndef FIELD_READER_H
#define FIELD_READER_H

#include <optional>
#include <jni.h>
#include <string>
#include <iostream>

class FieldReader {
private:
    JNIEnv *env;
    jobject jObj;
    jclass objClass;

public:
    FieldReader(JNIEnv *env, jobject jObj);
    std::optional<const char*> readString(const char* fieldName);
    std::optional<int> readInt(const char* fieldName);
    std::optional<bool> readBoolean(const char* fieldName);
};

#endif