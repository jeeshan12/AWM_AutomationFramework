package org.kira.automation.runner;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import org.kira.automation.annotations.Api;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.annotations.Web;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.web.StorageStateConfiguration;
import org.kira.automation.connector.RedisConnector;
import org.kira.automation.connector.RedisManager;
import org.kira.automation.constants.FrameworkConstants;
import org.kira.automation.exceptions.AnnotationMissingException;
import org.kira.automation.report.ExtentTestManager;
import org.kira.automation.utils.FileUtils;
import org.kira.automation.utils.JsonParserUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class TestSuiteHelper {

  private static final BiPredicate<Method, Boolean> SCREENSHOT_REQUIRED = (method, enabled) ->
    (method.isAnnotationPresent(Web.class) && enabled) ||
    (method.isAnnotationPresent(Mobile.class) && enabled);

  private TestSuiteHelper() {}

  /**
   * Method to return the framework configuration.
   *
   * @return {@link Configuration}
   */
  public static Configuration getConfiguration() {
    return JsonParserUtil.readJsonFile(
      FileUtils.readFileAsString(
        FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME
      ),
      Configuration.class
    );
  }

  static void addWebDriver(MethodContextImpl context) {
    Method method = context.method;

    // Check if the method is annotated with @Api, if so, set WebDriver to null and return
    if (method.isAnnotationPresent(Api.class)) {
      context.setWebDriver(null);
      return;
    }

    // Check if required annotations (@Mobile, @Web, @Api) are available
    if (!isRequiredAnnotationAvailable(method.getAnnotations())) {
      throw new AnnotationMissingException(
        "Please provide annotations like @Mobile, @Web and @Api to distinguish the tests"
      );
    }

    if (getConfiguration().getWeb().getCloud().isCloudExecutionEnabled()) {
      WebDriverSuiteHelper.setRemoteDriver(context, getConfiguration());
      return;
    }

    // Check if the method is not annotated with @Chrome or @Firefox but annotated with @Web
    if (
      !method.isAnnotationPresent(Chrome.class) &&
      !method.isAnnotationPresent(Firefox.class) &&
      method.isAnnotationPresent(Web.class)
    ) {
      WebDriverSuiteHelper.addDefaultWebDriver(context, getConfiguration());
      return;
    }

    // Set the WebDriver using WebDriverSuiteHelper
    WebDriverSuiteHelper.setWebDriver(context, getConfiguration());
  }

  static void setUpApiConfig(final MethodContextImpl context) {
    Method method = context.method;
    if (!method.isAnnotationPresent(Api.class)) {
      return;
    }
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    ApiSuiteHelper.setUpApiConfig(context, getConfiguration());
  }

  private static boolean isRequiredAnnotationAvailable(final Annotation[] annotations) {
    List<String> annotationsNameList = Arrays.stream(annotations)
      .map(annotation -> annotation.annotationType().getName())
      .toList();
    return Arrays.stream(FrameworkConstants.getMandateAnnotations()).anyMatch(
      annotationsNameList::contains
    );
  }

  static void takeScreenShot(MethodContextImpl context, Configuration configuration) {
    if (!isScreenShotEnabled(context, configuration)) {
      return;
    }
    String screenshot =
      ((TakesScreenshot) context.getWebDriver()).getScreenshotAs(OutputType.BASE64);
    context
      .getTest()
      .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
    context.getTest().addScreenCaptureFromBase64String(screenshot);
  }

  private static boolean isScreenShotEnabled(
    final MethodContextImpl context,
    final Configuration configuration
  ) {
    return (
      SCREENSHOT_REQUIRED.test(
        context.method,
        configuration.getWeb().getScreenshot().isEnabled()
      ) &&
      SCREENSHOT_REQUIRED.test(
        context.method,
        configuration.getMobile().getScreenshotConfiguration().isEnabled()
      )
    );
  }

  private static String getMethodNameWithClassName(final Method method) {
    return (method.getDeclaringClass().getSimpleName() + ":" + method.getName());
  }

  static void addTestReporting(final MethodContextImpl context) {
    context.setExtentTest(
      ExtentTestManager.startTest(
        getMethodNameWithClassName(context.method),
        context.method.getName()
      )
    );
  }

  static void takeScreenShotAndLogOnFailure(MethodContextImpl context, ITestResult testResult) {
    if (testResult.getStatus() == ITestResult.FAILURE) {
      if (!context.method.isAnnotationPresent(Api.class)) {
        takeScreenShot(context, getConfiguration());
      }
      context
        .getTest()
        .fail(
          MarkupHelper.createLabel(
            String.format("Test %s failed", context.method.getName()),
            ExtentColor.RED
          )
        );
    } else if (testResult.getStatus() == ITestResult.SUCCESS) {
      context
        .getTest()
        .pass(
          MarkupHelper.createLabel(
            String.format("Test %s passed", context.method.getName()),
            ExtentColor.GREEN
          )
        );
    } else {
      context
        .getTest()
        .skip(
          MarkupHelper.createLabel(
            String.format("Test %s skipped", context.method.getName()),
            ExtentColor.AMBER
          )
        );
    }
  }

  static void setUpRedis(SuiteContextImpl suiteContext) {
    StorageStateConfiguration storageStateConfiguration = TestSuiteHelper.getConfiguration()
      .getWeb()
      .getStorageStateConfiguration();
    if (
      storageStateConfiguration.isStorageStateEnabled() &&
      storageStateConfiguration.isRedisEnabled()
    ) {
      RedisManager redisManager = new RedisManager(
        storageStateConfiguration.getRedisUrl(),
        storageStateConfiguration.getRedisPort(),
        storageStateConfiguration.getStorageStateKey(),
        RedisConnector.getInstance()
      );
      suiteContext.setRedisManager(redisManager);
    }
  }
}
