package dev.vagvolgyi.rgbmatrix.wrapper;

import dev.vagvolgyi.rgbmatrix.jni.FontJNI;

import java.io.File;
import java.net.URL;

public class BdfFont {
    private final FontJNI font;

    public BdfFont(File fontFile) {
        this.font = new FontJNI(fontFile.getAbsolutePath());
    }

    public BdfFont(URL fontUrl) {
        this.font = new FontJNI(fontUrl.getFile());
    }

    FontJNI getFont() {
        return font;
    }
}
