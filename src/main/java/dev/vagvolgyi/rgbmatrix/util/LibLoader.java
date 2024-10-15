package dev.vagvolgyi.rgbmatrix.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class LibLoader {
    private static final Logger LOGGER = Logger.getLogger(LibLoader.class.getName());
    private static final String LIB_NAME = "librgbmatrixjni.so";

    private LibLoader() {}

    public static void loadJniLib() {
        try(InputStream inputStream = LibLoader.class.getResourceAsStream("/" + LIB_NAME)) {
            if(inputStream == null) {
                throw new IOException("Resource not found: " + LIB_NAME);
            }

            String[] libNameParts = LIB_NAME.split("\\.");
            Path tempFile = Files.createTempFile(libNameParts[0], "." + libNameParts[1]);
            tempFile.toFile().deleteOnExit();

            Files.copy(inputStream, tempFile, REPLACE_EXISTING);

            System.load(tempFile.toAbsolutePath().toString());
        }
        catch(IOException e) {
            LOGGER.severe("Unable to load the JNI library: " + e.getMessage());
        }
    }
}
