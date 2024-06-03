package org.kira.automation.framework;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.kira.automation.report.ExtentTestManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtentTestManagerTest {

  private ExtentReports mockExtentReports;
  private ExtentTest mockExtentTest;

  @BeforeMethod
  public void setUp() throws Exception {
    this.mockExtentReports = mock(ExtentReports.class);
    this.mockExtentTest = mock(ExtentTest.class);

    this.setStaticFieldToAccessible(ExtentTestManager.class, "extent", this.mockExtentReports);
    this.setStaticFieldToAccessible(ExtentTestManager.class, "extentTestMap", new HashMap<>());
  }

  @Test
  public void testStartTest() {
    final String testName = "Unit  Test";
    final String description = "Unit  Test Description";
    when(this.mockExtentReports.createTest(testName, description)).thenReturn(this.mockExtentTest);
    ExtentTest test = ExtentTestManager.startTest(testName, description);
    assertNotNull(test);
    assertEquals(test, this.mockExtentTest);
    verify(this.mockExtentReports, times(1)).createTest(testName, description);
  }

  @Test
  public void testGetTest() {
    final String testName = "Unit  Test";
    final String description = "Unit  Test Description";

    when(this.mockExtentReports.createTest(testName, description)).thenReturn(this.mockExtentTest);
    ExtentTestManager.startTest(testName, description);

    ExtentTest test = ExtentTestManager.getTest();

    assertNotNull(test);
    assertEquals(test, this.mockExtentTest);
  }

  @Test
  public void testEndTest() {
    final String testName = "Unit  Test";
    final String description = "Unit  Test Description";

    when(this.mockExtentReports.createTest(testName, description)).thenReturn(this.mockExtentTest);
    ExtentTestManager.startTest(testName, description);

    ExtentTest mockExtentTestFromMap = ExtentTestManager.getTest();
    when(mockExtentTestFromMap.getExtent()).thenReturn(this.mockExtentReports);

    ExtentTestManager.endTest();

    verify(this.mockExtentReports, times(1)).flush();
  }

  private void setStaticFieldToAccessible(Class<?> clazz, String fieldName, Object value)
    throws Exception {
    Field field = clazz.getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(null, value);
  }
}
