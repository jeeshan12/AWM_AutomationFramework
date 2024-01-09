package org.kira.automation.runner;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;
import org.kira.automation.annotations.Api;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.constants.FrameworkConstants;
import org.kira.automation.utils.FileUtils;
import org.kira.automation.utils.JsonParserUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestSuiteRunner {
    private final ThreadLocal<MethodContextImpl> methodContextThreadLocal = new ThreadLocal<>();
    private final Configuration configuration = JsonParserUtil.readJsonFile (FileUtils.readFileAsString (
        FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME
        ), Configuration.class
    );

    @BeforeMethod
    public void setUp(Method method) {
        MethodContextImpl methodContext = new MethodContextImpl(method);
        TestSuiteHelper.addWebDriver(methodContext, configuration);
        TestSuiteHelper.addTestReporting (methodContext);
        methodContextThreadLocal.set(methodContext);
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult testResult)  {

        MethodContextImpl context = methodContextThreadLocal.get();
        if (context == null) {
            return;
        }

        if (!context.method.isAnnotationPresent (Api.class)) {
            TestSuiteHelper.takeScreenShotAndLogOnFailure(context, configuration, testResult);
            context.getWebDriver().quit();
        }
        context.flushReport ();
        methodContextThreadLocal.remove();
    }

    protected WebDriver getDriver() {
        MethodContextImpl context = methodContextThreadLocal.get();
        return  context.getWebDriver ();
    }

    protected ExtentTest getExtentTest() {
        MethodContextImpl context =  methodContextThreadLocal.get ();
        return context.getTest ();
    }

}
