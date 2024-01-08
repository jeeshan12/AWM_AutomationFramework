package org.kira.automation.report;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static volatile ExtentReports extentReports;

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            synchronized (ExtentManager.class) {
                if (extentReports == null) {
//                    final File CONF = new File ("src/test/resources/spark.config.json");
                    extentReports = new ExtentReports();
                    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/spark/spark.html");
                    sparkReporter.config().setReportName("Extent Report");
                    extentReports.attachReporter(sparkReporter);
                    extentReports.setSystemInfo("Author", "Mohammad Jeeshan");
                }
            }
        }
        return extentReports;
    }
}
