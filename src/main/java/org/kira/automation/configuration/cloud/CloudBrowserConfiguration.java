package org.kira.automation.configuration.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class CloudBrowserConfiguration {

  private String os;

  @SerializedName("os_version")
  private String osVersion;

  @SerializedName("browser_version")
  private String browserVersion;

  private String resolution;
}
