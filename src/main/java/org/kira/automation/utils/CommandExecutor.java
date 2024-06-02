package org.kira.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.kira.automation.exceptions.FrameworkGenericException;

public class CommandExecutor {

  private CommandExecutor() {}

  public static String executeCommand(String shellPath, String command) {
    StringBuilder builder = new StringBuilder();
    File bashExecutable = new File(shellPath);

    ProcessBuilder processBuilder = new ProcessBuilder().inheritIO();
    processBuilder.directory(new File(System.getProperty("user.dir")));
    processBuilder.command(bashExecutable.getAbsolutePath(), "-c", command);

    try {
      Process process = processBuilder.start();
      BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(process.getInputStream())
      );
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
