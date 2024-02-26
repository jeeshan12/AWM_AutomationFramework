package org.kira.automation.configuration.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class ApiLoggingConfiguration {

  private boolean request;
  private boolean response;
}
