package org.kira.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.kira.automation.exceptions.FrameworkGenericException;

public class EmulatorUtil {

  private EmulatorUtil() {
  }

  public static void startEmulator(String adbPath, String emulatorName) {
    File adbFile = new File(adbPath);
    if (!adbFile.exists()) {
      System.err.println(String.format("ADB not found at the specified path : %s", adbPath));
      return;
    }

    String commandOutput = executeCommand(adbPath + " devices");
    if (commandOutput.contains(emulatorName)) {
      System.err.println(String.format("Emulator %s is already running", emulatorName));
      return;
    }
    executeCommand(adbPath + " emulator -avd " + emulatorName);
  }

  private static String executeCommand(String command) {
    StringBuilder builder = new StringBuilder();
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "-c", command);

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
      throw new FrameworkGenericException("Error while starting emulator", e);
    }
    return builder.toString();
  }
}
