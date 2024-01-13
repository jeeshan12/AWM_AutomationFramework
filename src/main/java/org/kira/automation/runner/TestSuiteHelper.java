package org.kira.automation.runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.kira.automation.annotations.Api;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.annotations.Web;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.constants.FrameworkConstants;
import org.kira.automation.exceptions.AnnotationMissingException;
import org.kira.automation.report.ExtentTestManager;
import org.kira.automation.utils.FileUtils;
import org.kira.automation.utils.JsonParserUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class TestSuiteHelper {
    private TestSuiteHelper(){}
    private static final BiPredicate<Method, Boolean> SCREENSHOT_REQUIRED= (method, enabled) -> method.isAnnotationPresent (Web.class) && enabled ||
        method.isAnnotationPresent (Mobile.class) && enabled;

    /**
     * Method to return the framework configuration.
     * @return {@link Configuration}
     */
     public static Configuration getConfiguration() {
         return JsonParserUtil.readJsonFile (FileUtils.readFileAsString (
                 FrameworkConstants.TEST_RESOURCE_FOLDER + FrameworkConstants.CONFIG_FILE_NAME
             ), Configuration.class);
     }
    static void addWebDriver(MethodContextImpl context) {
        Method method = context.method;
        if (method.isAnnotationPresent (Api.class)) {
            context.setWebDriver (null);
            return;
        }

        if(!isRequiredAnnotationAvailable(method.getAnnotations ())) {
            throw new AnnotationMissingException (
                "Please provide annotations like @Mobile, @Web and @Api to distinguish the tests"
            );
        }

        if (!method.isAnnotationPresent (Chrome.class) && !method.isAnnotationPresent (Firefox.class)) {
            WebDriverSuiteHelper.addDefaultWebDriver (context, getConfiguration());
            return;
        }
        WebDriverSuiteHelper.setWebDriver(context, getConfiguration());
    }


    static void setUpApiConfig (final MethodContextImpl context) {
        Method method = context.method;
        if (!method.isAnnotationPresent (Api.class)) return;
        ApiSuiteHelper.setUpApiConfig (context, getConfiguration ());
    }


    private static boolean isRequiredAnnotationAvailable (final Annotation[] annotations) {
        List<String> annotationsNameList = Arrays.stream (annotations)
            .map (annotation -> annotation.annotationType ()
                .getName ())
            .toList ();
        return Arrays.stream (FrameworkConstants.getMandateAnnotations())
            .anyMatch (annotationsNameList::contains);
    }

     static void takeScreenShot(MethodContextImpl context,  Configuration configuration ) {
        if (!isScreenShotEnabled(context, configuration)
        ) {
            return;
        }
         String screenshot = ((TakesScreenshot) context.getWebDriver())
                .getScreenshotAs(OutputType.BASE64);
         context.getTest ().fail(MediaEntityBuilder.createScreenCaptureFromBase64String (screenshot).build());
         context.getTest ().addScreenCaptureFromBase64String (screenshot);
    }

    private static boolean isScreenShotEnabled (final MethodContextImpl context, final Configuration configuration) {
        return SCREENSHOT_REQUIRED.test(context.method, configuration.getWeb().getScreenshot().isEnabled()) &&
            SCREENSHOT_REQUIRED.test(context.method, configuration.getMobile().getScreenshot().isEnabled());
    }

    private static String getMethodNameWithClassName (final Method method) {
        return method.getDeclaringClass().getSimpleName () + ":" + method.getName();
    }

     static void addTestReporting (final MethodContextImpl context) {
        context.setExtentTest (
            ExtentTestManager.startTest (getMethodNameWithClassName(context.method), context.method.getName ())
        );
    }

    static void takeScreenShotAndLogOnFailure (MethodContextImpl context, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            if (!context.method.isAnnotationPresent (Api.class)) {
                takeScreenShot (context, getConfiguration());
            }
            context.getTest ().fail (
                MarkupHelper.createLabel(String.format ( "Test %s failed", context.method.getName () ), ExtentColor.RED));
        } else if (testResult.getStatus() == ITestResult.SUCCESS ){
            context.getTest ().pass (
                MarkupHelper.createLabel(String.format ( "Test %s passed", context.method.getName () ), ExtentColor.GREEN));
        } else {
            context.getTest ().skip (
                MarkupHelper.createLabel(String.format ( "Test %s skipped", context.method.getName () ), ExtentColor.AMBER));
        }
    }
}
