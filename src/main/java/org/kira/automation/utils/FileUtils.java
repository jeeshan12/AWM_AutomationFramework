package org.kira.automation.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.NonNull;

public final class FileUtils {

    public static String readFileAsString( @NonNull String filePath)  {
        byte[] encodedBytes = new byte[0];
        try {
            encodedBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException (String.format ("File with name %s not found. Exception : %s" , filePath, e.toString ()));
        }
        return new String(encodedBytes);
    }
}
