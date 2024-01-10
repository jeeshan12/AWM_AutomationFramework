
package org.kira.automation.utils;

import com.google.gson.Gson;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.kira.automation.runner.TestSuiteHelper;

public final class JsonParserUtil {

    private static final Gson gson = new Gson();

    private JsonParserUtil() {
        throw new FrameworkGenericException ("Can not use constructor to create the object of this class");
    }

    public static <T> T readJsonFile(final String fileContent, Class<T> classz) {
        return gson.fromJson(fileContent, classz);
    }
}
