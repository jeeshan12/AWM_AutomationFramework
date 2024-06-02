package org.kira.automation.framework;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.kira.automation.utils.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUtilsTest {

  private String validFilePath;
  private String invalidFilePath;

  @BeforeMethod
  public void setUp() throws IOException {
    validFilePath = "testfile.txt";
    invalidFilePath = "nonexistentfile.txt";

    Files.write(Paths.get(validFilePath), "Unit Test Content".getBytes());
  }

  @AfterMethod
  public void tearDown() throws IOException {
    Files.deleteIfExists(Paths.get(validFilePath));
  }

  @Test
  public void testReadFileAsStringSuccess() {
    String content = FileUtils.readFileAsString(validFilePath);
    assertEquals(content, "Unit Test Content");
  }

  @Test(expectedExceptions = FrameworkGenericException.class)
  public void testReadFileAsStringFileNotFound() {
    FileUtils.readFileAsString(invalidFilePath);
  }
}
