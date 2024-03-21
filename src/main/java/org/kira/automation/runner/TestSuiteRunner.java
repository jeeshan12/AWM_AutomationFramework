package org.kira.automation.runner;

import static org.kira.automation.runner.TestSuiteHelper.addTestReporting;
import static org.kira.automation.runner.TestSuiteHelper.addWebDriver;
import static org.kira.automation.runner.TestSuiteHelper.setUpApiConfig;
import static org.kira.automation.runner.TestSuiteHelper.setUpRedis;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.reflect.Method;
import org.json.JSONObject;
import org.kira.automation.annotations.Api;
import org.kira.automation.configuration.api.GraphQLQuery;
import org.kira.automation.connector.RedisManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestSuiteRunner {

  private final ThreadLocal<MethodContextImpl> methodContextThreadLocal = new ThreadLocal<>();

  private final ThreadLocal<SuiteContextImpl> suiteContextThreadLocal = new ThreadLocal<>();

  @BeforeSuite
  public void setUpSuiteConfigs() {
    SuiteContextImpl suiteContext = new SuiteContextImpl();
    setUpRedis(suiteContext);
    suiteContextThreadLocal.set(suiteContext);
  }

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

  @AfterSuite(alwaysRun = true)
  public void cleanUpStates() {
    SuiteContextImpl suiteContext = suiteContextThreadLocal.get();
    if (suiteContext.getRedisManager() != null) {
      suiteContext.getRedisManager().clearState();
    }
    suiteContextThreadLocal.remove();
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

  protected GraphQLQuery getGraphQlQueryInstance() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getGraphQlQueryInstance();
  }

  protected JSONObject getGraphQlQueryVariablesObject() {
    MethodContextImpl context = methodContextThreadLocal.get();
    return context.getGraphQlQueryVariablesObject();
  }

  protected RedisManager getRedisManager() {
    SuiteContextImpl context = suiteContextThreadLocal.get();
    return context.getRedisManager();
  }
}
