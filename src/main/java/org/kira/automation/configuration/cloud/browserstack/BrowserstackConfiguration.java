package org.kira.automation.configuration.cloud.browserstack;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.cloud.CloudBrowserConfiguration;

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
  private CloudBrowserConfiguration chromeConfig;

  @SerializedName("firefox")
  private CloudBrowserConfiguration firefoxConfig;

  @SerializedName("safari")
  private CloudBrowserConfiguration safariConfig;

  @SerializedName("edge")
  private CloudBrowserConfiguration edgeConfig;

}
