package org.kira.automation.runner;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;
import org.kira.automation.report.ExtentTestManager;
import org.openqa.selenium.WebDriver;

public class MethodContextImpl implements MethodContext{

    final       Method       method;
    private     WebDriver    webDriver;

    private ExtentTest extentTest;


    public MethodContextImpl(Method method) {
        this.method = method;
    }

    @Override
    public WebDriver getWebDriver () {
        return this.webDriver;
    }

    @Override
    public ExtentTest getTest () {
        return this.extentTest;
    }

    @Override
    public void flushReport () {
        this.extentTest.getExtent ().flush ();
    }

    public void setWebDriver (final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public  void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }
}
