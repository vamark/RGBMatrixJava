#include "color_converter.h"

ColorConverter::ColorConverter(JNIEnv *env) {
    this->env = env;
    colorClass = env->FindClass("java/awt/Color");
}

Color* ColorConverter::convert(jobject jColor) {
    if (jColor == nullptr) {
        return NULL;
    }

    int r = env->CallIntMethod(jColor, env->GetMethodID(colorClass, "getRed", "()I"));
    int g = env->CallIntMethod(jColor, env->GetMethodID(colorClass, "getGreen", "()I"));
    int b = env->CallIntMethod(jColor, env->GetMethodID(colorClass, "getBlue", "()I"));

    return new Color(r, g, b);
}