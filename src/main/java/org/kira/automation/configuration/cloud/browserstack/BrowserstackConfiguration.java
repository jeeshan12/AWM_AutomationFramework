package org.kira.automation.configuration.cloud.browserstack;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.cloud.CloudBrowserConfig;

@ToString
@AllArgsConstructor
@Getter
public class BrowserstackConfiguration {

  private String sessionName;

  private String buildName;

  private String projectName;

  private boolean allowAllCookies;

  private String userName;

  private String accessKey;

  private String browserName;

  private String video;

  private String networkLogs;

  private String debug;

  @SerializedName("chrome")
  private CloudBrowserConfig chromeConfig;

  @SerializedName("firefox")
  private CloudBrowserConfig firefoxConfig;

  @SerializedName("safari")
  private CloudBrowserConfig safariConfig;

  @SerializedName("edge")
  private CloudBrowserConfig edgeConfig;

}
