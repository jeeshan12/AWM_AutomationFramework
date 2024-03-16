package org.kira.automation.configuration.mobile;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class DeviceLockConfiguration {

  @SerializedName("skipUnlock")
  private boolean deviceUnlock;

  private String unlockType;

  private String unlockKey;

  private String unlockStrategy;

  private int unlockSuccessTimeout;

  @SerializedName("enabled")
  private boolean isDeviceLockEnabled;
}
