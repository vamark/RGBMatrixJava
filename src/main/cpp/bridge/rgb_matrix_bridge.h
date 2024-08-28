#ifndef RGB_MATRIX_BRIDGE_H
#define RGB_MATRIX_BRIDGE_H

#include <jni.h>
#include "bridge.h"
#include "led-matrix.h"

using rgb_matrix::RGBMatrix;

class RGBMatrixBridge : public Bridge<RGBMatrix> {
private:
    JNIEnv* env;

public:
    RGBMatrixBridge(JNIEnv* env) : Bridge(env) {}
    jobject toJava(RGBMatrix* matrix);
};

#endif