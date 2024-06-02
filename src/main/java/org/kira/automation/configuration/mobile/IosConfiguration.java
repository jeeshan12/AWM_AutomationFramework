package org.kira.automation.configuration.mobile;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IosConfiguration extends CommonMobileConfiguration {

  private int wdaLocalPort;

  @SerializedName("isHeadless")
  private boolean headless;

  @SerializedName("options")
  private List<String> additionalCapabilities;
}
