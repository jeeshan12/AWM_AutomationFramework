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
public class AndroidConfiguration extends CommonMobileConfiguration{

  @Builder.Default
  private String appActivity = "";

  @Builder.Default
  private String appPackage = "";

  @SerializedName("isHeadless")
  private boolean headless;

  private int systemPort;

  @SerializedName("isParallel")
  private boolean parallel;

  @SerializedName("options")
  private List<String> additionalCapabilities;

  @SerializedName("deviceLock")
  private DeviceLockConfiguration deviceLockConfiguration;

  private String chromedriverExecutable;
}
