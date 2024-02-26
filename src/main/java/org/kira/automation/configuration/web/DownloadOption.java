package org.kira.automation.configuration.web;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class DownloadOption {

  private boolean isDownloadRequired;
  private List<String> downloadOptions;
}
