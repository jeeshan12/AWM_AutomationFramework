package org.kira.automation.configuration.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.cloud.browserstack.BrowserstackConfiguration;
import org.kira.automation.configuration.cloud.lambdatest.LambdatestConfiguration;

@ToString
@AllArgsConstructor
@Getter
public class CloudProviderConfiguration {

  @SerializedName("browserstack")
  private BrowserstackConfiguration browserstackConfiguration;

  @SerializedName("lambdatest")
  private LambdatestConfiguration lambdatestConfiguration;
}
