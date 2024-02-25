package org.kira.automation.configuration;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class GridConfiguration {

  @SerializedName("enabled")
  private boolean isGridEnabled;

  @SerializedName("url")
  private boolean gridUrl;

  @SerializedName("port")
  private boolean gridPort;

}