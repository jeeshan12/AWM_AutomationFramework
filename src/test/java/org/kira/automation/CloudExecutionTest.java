package org.kira.automation;

import com.aventstack.extentreports.Status;
import org.kira.automation.annotations.WebCloud;
import org.kira.automation.annotations.Web;
import org.kira.automation.runner.TestSuiteRunner;
import org.testng.annotations.Test;

public class CloudExecutionTest extends TestSuiteRunner {


    @Test
    @Web
    @WebCloud
    public void testFirefoxBrowser() {
        getExtentTest().log (Status.INFO, "Opening Browser");
        getDriver ().navigate ().to ("https://google.com/");
    }

}
