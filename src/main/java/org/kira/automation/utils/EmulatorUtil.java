package org.kira.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.testng.log4testng.Logger;

public class EmulatorUtil {
  private static final Logger LOGGER = Logger.getLogger(EmulatorUtil.class);

  private EmulatorUtil() {
  }

  public static void startEmulator(String adbPath, String emulatorName) {
    File adbFile = new File(adbPath);
    if (!adbFile.exists()) {
      LOGGER.error(String.format("ADB not found at the specified path : %s", adbPath));
      return;
    }

    String commandOutput = executeCommand(adbPath + " devices");
    if (commandOutput.contains(emulatorName)) {
      LOGGER.error(String.format("Emulator %s is already running", emulatorName));
      return;
    }
    executeCommand(adbPath + " emulator -avd " + emulatorName);
  }

  private static String executeCommand(String command) {
    StringBuilder builder = new StringBuilder();
    File bashExecutable = new File("/bin/bash");

    ProcessBuilder processBuilder = new ProcessBuilder().inheritIO();
    processBuilder.directory(new File(System.getProperty("user.dir")));
    processBuilder.command(bashExecutable.getAbsolutePath(), "-c", command);

    try {
      Process process = processBuilder.start();
      BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        builder.append(line).append(System.lineSeparator());
      }
      process.waitFor();
    } catch (InterruptedException | IOException e) {
      Thread.currentThread().interrupt();
      throw new FrameworkGenericException("Error while starting emulator", e);
    }
    return builder.toString();
  }
}
