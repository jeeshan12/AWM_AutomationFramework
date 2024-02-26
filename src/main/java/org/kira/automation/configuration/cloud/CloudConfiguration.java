package org.kira.automation.configuration.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class CloudConfiguration {

  @SerializedName("enabled")
  private boolean isCloudExecutionEnabled;

  private String platform;

  @SerializedName("option")
  private String cloudProvider;

  private CloudProviderConfiguration provider;
}
