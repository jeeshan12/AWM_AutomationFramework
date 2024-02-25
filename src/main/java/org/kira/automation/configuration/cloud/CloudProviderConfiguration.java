package org.kira.automation.configuration.cloud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.cloud.browserstack.BrowserstackConfiguration;

@ToString
@AllArgsConstructor
@Getter
public class CloudProviderConfiguration {

  private BrowserstackConfiguration browserstack;

}
