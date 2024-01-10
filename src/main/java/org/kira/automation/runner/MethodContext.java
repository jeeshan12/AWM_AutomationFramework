package org.kira.automation.runner;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public interface MethodContext {

    WebDriver getWebDriver();
    ExtentTest getTest();
    void flushReport();

}
