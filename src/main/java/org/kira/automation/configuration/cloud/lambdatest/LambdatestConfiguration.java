package org.kira.automation.configuration.cloud.lambdatest;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.cloud.CloudBrowserConfiguration;

@ToString
@AllArgsConstructor
@Getter
public class LambdatestConfiguration {

  private String buildName;

  private String projectName;

  private String userName;

  private String accessKey;

  private String browserName;

  private String console;

  private String networkThrottling;

  private String timezone;

  private boolean network;

  private boolean headless;

  private boolean video;

  private boolean visual;

  private String seleniumVersion;

  private boolean tunnel;

  @SerializedName("chrome")
  private CloudBrowserConfiguration chromeConfig;

  @SerializedName("firefox")
  private CloudBrowserConfiguration firefoxConfig;

  @SerializedName("safari")
  private CloudBrowserConfiguration safariConfig;

  @SerializedName("edge")
  private CloudBrowserConfiguration edgeConfig;

  @SerializedName("ie")
  private CloudBrowserConfiguration ieConfig;
}
