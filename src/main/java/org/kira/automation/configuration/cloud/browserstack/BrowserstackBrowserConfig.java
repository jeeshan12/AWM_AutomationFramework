package org.kira.automation.configuration.cloud.browserstack;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class BrowserstackBrowserConfig {

  private String os;

  @SerializedName("os_version")
  private String osVersion;

  @SerializedName("browser_version")
  private String browserVersion;

  private String resolution;
}
