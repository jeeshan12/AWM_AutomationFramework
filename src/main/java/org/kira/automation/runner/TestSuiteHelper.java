package org.kira.automation.runner;

import static org.kira.automation.constants.FrameworkConstants.BROWSER;
import static org.kira.automation.constants.FrameworkConstants.CHROME;
import static org.kira.automation.constants.FrameworkConstants.DEFAULT_SCREENSHOTS_FOLDER;
import static org.kira.automation.constants.FrameworkConstants.FIREFOX;
import static org.kira.automation.constants.FrameworkConstants.MANDATE_ANNOTATIONS;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.codec.binary.Base64;
import org.kira.automation.annotations.Android;
import org.kira.automation.annotations.Api;
import org.kira.automation.annotations.Chrome;
import org.kira.automation.annotations.Firefox;
import org.kira.automation.annotations.Mobile;
import org.kira.automation.annotations.Web;
import org.kira.automation.annotations.iOS;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.exceptions.AnnotationMissingException;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.kira.automation.factory.WebDriverFactorySupplier;
import org.kira.automation.report.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

public class TestSuiteHelper {

    private TestSuiteHelper(){}

    static void addWebDriver(MethodContextImpl context, final Configuration configuration) {
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
            addDefaultWebDriver (context, configuration);
            return;
        }
        
        setWebDriver(context, configuration);

    }

    private static void setWebDriver (final MethodContextImpl context, final Configuration configuration) {
        Method method = context.method;
        if (method.isAnnotationPresent (Chrome.class)
            || CHROME.equalsIgnoreCase (System.getenv (BROWSER))) {
            addChromeDriver(context, configuration);
        } else if (method.isAnnotationPresent (Firefox.class) ||
            FIREFOX.equalsIgnoreCase (System.getenv (BROWSER))) {
            addFirefoxDriver(context, configuration);
        } else if (method.isAnnotationPresent (iOS.class) ){
            addAndroidDriver(context, configuration);
        } else if (method.isAnnotationPresent (Android.class)) {
            addiOSDriver(context, configuration);
        } else {
            throw new AnnotationMissingException (
                "Please provide valid annotations like  like @Chrome, @Firefox, @iOS and @Android to initialise the webdriver"
            );
        }
    }

    private static boolean isRequiredAnnotationAvailable (final Annotation[] annotations) {
        List<String> annotationsNameList = Arrays.stream (annotations)
            .map (annotation -> annotation.annotationType ()
                .getName ())
            .toList ();
        return Arrays.stream (MANDATE_ANNOTATIONS)
            .anyMatch (annotationsNameList::contains);
    }

    private static void addDefaultWebDriver (final MethodContextImpl context, final Configuration configuration) {
        context.setWebDriver (
            WebDriverFactorySupplier.getWebDriver (configuration.getWeb ().getBrowser ()).getWebDriver (configuration)
        );
    }

    private static void addiOSDriver (final MethodContextImpl context, final Configuration configuration) {
        // TODO document why this method is empty
    }

    private static void addAndroidDriver (final MethodContextImpl context, final Configuration configuration) {
        // TODO document why this method is empty
    }

    private static void addFirefoxDriver (final MethodContextImpl context, final Configuration configuration) {
        context.setWebDriver (
            WebDriverFactorySupplier.getWebDriver (FIREFOX).getWebDriver (configuration)
        );
    }
    private static void addChromeDriver (final MethodContextImpl context, final Configuration configuration) {
        context.setWebDriver (
            WebDriverFactorySupplier.getWebDriver (CHROME).getWebDriver (configuration)
        );
    }

     static void takeScreenShot(MethodContextImpl context,  Configuration configuration ) {
        if (!(context.method.isAnnotationPresent (Web.class) && configuration.getWeb ()
            .getScreenshot ().isEnabled ()) ||
            !(context.method.isAnnotationPresent (Mobile.class) && configuration.getMobile ()
                .getScreenshot ()
                .isEnabled ())
        ) {
            return;
        }


         System.out.println ("I am in");


         String screenshot = ((TakesScreenshot) context.getWebDriver())
                .getScreenshotAs(OutputType.BASE64);
         context.getTest ().fail(MediaEntityBuilder.createScreenCaptureFromBase64String (screenshot).build());
         context.getTest ().log (Status.FAIL, String.format ( "Test %s failed", context.method.getName () ));
         context.getTest ().addScreenCaptureFromBase64String (screenshot);
    }

    private static String getMethodNameWithClassName (final Method method) {
        return method.getDeclaringClass().getSimpleName () + ":" + method.getName();
    }

     static void addTestReporting (final MethodContextImpl context) {
        context.setExtentTest (
            ExtentTestManager.startTest (getMethodNameWithClassName(context.method), context.method.getName ())
        );
    }

    static void takeScreenShotAndLogOnFailure (MethodContextImpl context, Configuration configuration, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE && !context.method.isAnnotationPresent (Api.class)) {
            takeScreenShot (context, configuration);
        } else if (testResult.getStatus() == ITestResult.SUCCESS ){
            context.getTest ().log (Status.PASS, String.format ("Test %s passed", context.method.getName ()));
        } else {
            context.getTest ().log (Status.SKIP, String.format ("Test %s Skipped", context.method.getName ()));
        }

    }
}
