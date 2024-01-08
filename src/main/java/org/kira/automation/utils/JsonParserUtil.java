
package org.kira.automation.utils;

import com.google.gson.Gson;

public final class JsonParserUtil {

    private static final Gson gson = new Gson();

    public JsonParserUtil() {
        throw new RuntimeException ("Can not use constructor to create the object of this class");
    }

    public static <T> T readJsonFile(final String fileContent, Class<T> classz) {
        return gson.fromJson(fileContent, classz);
    }
}
