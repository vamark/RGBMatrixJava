package io.vagvolgyi.ledmatrix;

import io.vagvolgyi.ledmatrix.jni.RGBMatrixJNI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RGBMatrixJNI.initMatrix();
        RGBMatrixJNI.setPixel(11, 22, 133, 144, 155);
        RGBMatrixJNI.destroyMatrix();
    }
}