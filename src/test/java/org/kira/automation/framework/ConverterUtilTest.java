package org.kira.automation.framework;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.kira.automation.utils.ConverterUtil;
import org.testng.annotations.Test;

public class ConverterUtilTest {

  @Test
  public void testIsNumericWithNumericString() {
    // Arrange
    String numericString = "123";

    // Act
    boolean result = ConverterUtil.isNumeric(numericString);

    // Assert
    assertTrue(result);
  }

  @Test
  public void testIsNumericWithNonNumericString() {
    // Arrange
    String nonNumericString = "abc";

    // Act
    boolean result = ConverterUtil.isNumeric(nonNumericString);

    // Assert
    assertFalse(result);
  }

  @Test
  public void testIsBooleanWithTrueString() {
    // Arrange
    String trueString = "true";

    // Act
    boolean result = ConverterUtil.isBoolean(trueString);

    // Assert
    assertTrue(result);
  }

  @Test
  public void testIsBooleanWithFalseString() {
    // Arrange
    String falseString = "false";

    // Act
    boolean result = ConverterUtil.isBoolean(falseString);

    // Assert
    assertTrue(result);
  }

  @Test
  public void testIsBooleanWithOtherString() {
    // Arrange
    String otherString = "hello";

    // Act
    boolean result = ConverterUtil.isBoolean(otherString);

    // Assert
    assertFalse(result);
  }
}
