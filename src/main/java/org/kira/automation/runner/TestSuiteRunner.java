package org.kira.automation.runner;

import static org.kira.automation.runner.TestSuiteHelper.addTestReporting;
import static org.kira.automation.runner.TestSuiteHelper.addWebDriver;
import static org.kira.automation.runner.TestSuiteHelper.setUpApiConfig;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.reflect.Method;
import org.kira.automation.annotations.Api;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestSuiteRunner {

  private final ThreadLocal<MethodContextImpl> methodContextThreadLocal = new ThreadLocal<>();

  @BeforeMethod
  public void setUp(Method method) {
    MethodContextImpl methodContext = new MethodContextImpl(method);
    setUpApiConfig(methodContext);
    addWebDriver(methodContext);
    addTestReporting(methodContext);
    methodContextThreadLocal.set(methodContext);
  }


  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult testResult) {
    MethodContextImpl context = methodContextThreadLocal.get();
    if (context == null) {
      return;
    }
    TestSuiteHelper.takeScreenShotAndLogOnFailure(context, testResult);
    if (!context.method.isAnnotationPresent(Api.class)) {
      context.getWebDriver().quit();
    }
    context.flushReport();
    methodContextThreadLocal.remove();
  }

  protected WebDriver getDriver() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getWebDriver();
  }

  protected ExtentTest getExtentTest() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getTest();
  }

  protected RequestSpecBuilder getRequestSpecBuilder() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getRequestSpecBuilder();
  }

  protected ResponseSpecBuilder getResponseSpecBuilder() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getResponseSpecBuilder();
  }

  protected RequestSpecification getRequestSpecification() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getRequestSpecification();
  }

  protected ResponseSpecification getResponseSpecification() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getResponseSpecification();
  }

}
