package org.kira.automation.configuration.web;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class ChromeOptionsConfig {

  private List<String> options;
  private DownloadOption downloadOption;

}
