package org.kira.automation.enums;

import lombok.Getter;

@Getter
public enum Backend {
  REST("rest"),
  GRAPHQL("graphql");

  private String api;

  Backend(String api) {
    this.api = api;
  }
}
