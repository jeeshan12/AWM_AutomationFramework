package org.kira.automation.configuration.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class DeviceLockConfiguration {

  private boolean skipUnlock;

  private String unlockType;

  private String unlockKey;

  private String unlockStrategy;

  private int unlockSuccessTimeout;
}
