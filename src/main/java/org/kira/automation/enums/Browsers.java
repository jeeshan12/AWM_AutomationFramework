package org.kira.automation.enums;

import lombok.Getter;

@Getter
public enum Browsers {
  CHROME("chrome"),
  FIREFOX("firefox");

  private final String name;

  Browsers(String name) {
    this.name = name;
  }
}
