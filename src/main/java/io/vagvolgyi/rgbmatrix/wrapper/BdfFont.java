package io.vagvolgyi.rgbmatrix.wrapper;

import io.vagvolgyi.rgbmatrix.jni.FontJNI;

import java.io.File;

public class BdfFont {
    private final FontJNI font;

    public BdfFont(File fontFile) {
        this.font = new FontJNI(fontFile.getAbsolutePath());
    }

    FontJNI getFont() {
        return font;
    }
}
