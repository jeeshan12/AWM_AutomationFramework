package org.kira.automation.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.kira.automation.exceptions.FrameworkGenericException;

import static org.kira.automation.constants.FrameworkConstants.DEFAULT_REPORTS_FOLDER;
import static org.kira.automation.constants.FrameworkConstants.REPORTS_CONFIG_JSON;

public class ExtentManager {

    private static class ExtentManagerHolder {
        static final File SPARK_CONFIG_FILE = Optional.ofNullable(new File (REPORTS_CONFIG_JSON))
            .orElseThrow(() -> new FrameworkGenericException("Please provide the path for spark config file to load the extent reports configuration"));
        private static final ExtentReports INSTANCE = createInstance(SPARK_CONFIG_FILE);
        private static ExtentReports createInstance(final File sparkConfigFile) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(DEFAULT_REPORTS_FOLDER);
            try {
                sparkReporter.loadJSONConfig (sparkConfigFile);
            } catch (IOException e) {
                throw new FrameworkGenericException (e.getMessage ());
            }
            ExtentReports extentReports = new ExtentReports();
            extentReports.setSystemInfo ("os", System.getProperty("os.name"));
            extentReports.attachReporter(sparkReporter);
            return extentReports;
        }
    }

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        return ExtentManagerHolder.INSTANCE;
    }

}
