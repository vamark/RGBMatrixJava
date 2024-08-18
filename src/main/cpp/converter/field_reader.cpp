#include "field_reader.h"

FieldReader::FieldReader(JNIEnv *env, jobject jObj) {
    this->env = env;
    this->jObj = jObj;
    this->objClass = env->GetObjectClass(jObj);
}

std::optional<std::string> FieldReader::readString(const char* fieldName) {
    jfieldID fieldId = env->GetFieldID(objClass, fieldName, "Ljava/lang/String;");
    jstring jStr = (jstring) env->GetObjectField(jObj, fieldId);

    if (jStr != nullptr) {
        const char *cStr = env->GetStringUTFChars(jStr, nullptr);
        std::string stdStr(cStr);
        env->ReleaseStringUTFChars(jStr, cStr);

        return stdStr;
    }

    return std::nullopt;
}

std::optional<int> FieldReader::readInt(const char* fieldName) {
    jfieldID fieldId = env->GetFieldID(objClass, fieldName, "Ljava/lang/Integer;");
    jobject jInt = env->GetObjectField(jObj, fieldId);

    if (jInt != nullptr) {
        return env->CallIntMethod(jInt, env->GetMethodID(env->FindClass("java/lang/Integer"), "intValue", "()I"));
    }

    return std::nullopt;
}

std::optional<bool> FieldReader::readBoolean(const char* fieldName) {
    jfieldID fieldId = env->GetFieldID(objClass, fieldName, "Ljava/lang/Boolean;");
    jobject jBool = env->GetObjectField(jObj, fieldId);

    if (jBool != nullptr) {
        return env->CallBooleanMethod(jBool, env->GetMethodID(env->FindClass("java/lang/Boolean"), "booleanValue", "()Z"));
    }

    return std::nullopt;
}