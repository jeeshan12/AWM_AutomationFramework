package org.kira.automation.configuration.mobile;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Builder
public class AndroidConfiguration {

  @Builder.Default
  private String appPath= "";

  private String browserName;

  private String platformName;

  private String automationName;

  private String platformVersion;

  @Builder.Default
  private String appActivity = "";

  @Builder.Default
  private String appName = "";

  @Builder.Default
  private String appUrl = "";

  private String deviceName;

  @Builder.Default
  private String appPackage = "";

  @Builder.Default
  private String udid = "";

  @SerializedName("isHeadless")
  private boolean headless;

  private int systemPort;

  @SerializedName("isParallel")
  private boolean parallel;

  @SerializedName("options")
  private List<String> additionalCapabilities;
}
