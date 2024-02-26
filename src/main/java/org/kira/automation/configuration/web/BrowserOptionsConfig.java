package org.kira.automation.configuration.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class BrowserOptionsConfig {

  private ChromeOptionsConfig chrome;
  private FirefoxOptionsConfig firefox;
}
