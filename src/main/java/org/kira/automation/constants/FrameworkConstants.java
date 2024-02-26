package org.kira.automation.constants;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.SystemUtils;
import org.kira.automation.runner.TestSuiteHelper;

public final class FrameworkConstants {
    private FrameworkConstants() {}
    public static final String USER_DIRECTORY = SystemUtils.getUserDir () + File.separator;
    public static final String TEST_RESOURCE_FOLDER = USER_DIRECTORY + "src" + File.separator + "test" + File.separator + "resources" + File.separator;

    public static final String MAIN_RESOURCE_FOLDER = USER_DIRECTORY + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    public static final String CONFIG_FILE_NAME = "config.json";
    public static final String DEFAULT_REPORTS_FOLDER = Optional.ofNullable (TestSuiteHelper.getConfiguration ().getReport ().getPath ()).orElse ("target/spark/spark.html");
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    public static final String EDGE = "edge";

    public static final String SAFARI = "safari";

    public static final String IE = "ie";

    public static final String OPERA = "opera";

    public static final String BROWSER = "browser";
    private static final String[] MANDATE_ANNOTATIONS = { "org.kira.automation.annotations.Web", "org.kira.automation.annotations.Api", "org.kira.automation.annotations.Mobile"};
    public static String[] getMandateAnnotations() {
        return Arrays.copyOf(MANDATE_ANNOTATIONS, MANDATE_ANNOTATIONS.length);
    }
    public static final String REPORTS_CONFIG_JSON = Optional.ofNullable (TestSuiteHelper.getConfiguration ().getReport ().getConfigPath ()).orElse ("src/test/resources/spark.config.json");
    public static final String CLOUD_USERNAME = "CLOUD_USERNAME";
    public static final String CLOUD_ACCESS_KEY = "CLOUD_ACCESS_KEY";
    public static final String BROWSER_VERSION = "browserVersion";
    public static final String OS = "os";
    public static final String OS_VERSION = "osVersion";
    public static final String BROWSER_NAME = "browserName";
    public static final String RESOLUTION = "resolution";
}
