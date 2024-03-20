package org.kira.automation.configuration.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CommonMobileConfiguration {

  private String browserName;

  private String deviceName;

  private String platformVersion;

  private String udid;

  private String app;
}
