package org.kira.automation.configuration.mobile;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.web.ScreenshotConfiguration;

@ToString
@AllArgsConstructor
@Getter
public class MobileConfiguration {

  @SerializedName("screenshot")
  ScreenshotConfiguration screenshotConfiguration;

  private String serverUrl;

  private int port;

  private int newCommandTimeout;

  private boolean noReset;

  private boolean fullReset;

  private boolean eventTimings;

  private boolean printPageSourceOnFindFailure;

  private String  platform;

  @SerializedName("android")
  private AndroidConfiguration androidConfiguration;



}
