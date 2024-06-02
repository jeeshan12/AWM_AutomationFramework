package org.kira.automation.utils;

import java.io.File;
import org.testng.log4testng.Logger;

public class EmulatorUtil {

  private static final Logger LOGGER = Logger.getLogger(EmulatorUtil.class);

  private EmulatorUtil() {}

  public static void startEmulator(String adbPath, String emulatorName) {
    File adbFile = new File(adbPath);
    if (!adbFile.exists()) {
      LOGGER.error(String.format("ADB not found at the specified path : %s", adbPath));
      return;
    }

    String commandOutput = CommandExecutor.executeCommand("/bin/bash", adbPath + " devices");
    if (commandOutput.contains(emulatorName)) {
      LOGGER.error(String.format("Emulator %s is already running", emulatorName));
      return;
    }
    CommandExecutor.executeCommand("/bin/bash", adbPath + " emulator -avd " + emulatorName);
  }
}
