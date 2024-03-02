package org.kira.automation.configuration.web;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.GridConfiguration;
import org.kira.automation.configuration.cloud.CloudConfiguration;

@ToString
@AllArgsConstructor
@Getter
public class WebConfiguration {

  private ScreenshotConfiguration screenshot;
  private String browser;
  private boolean isHeadless;
  private BrowserOptionsConfig browserOptions;
  private CloudConfiguration cloud;
  @SerializedName("grid")
  private GridConfiguration seleniumGrid;

  @SerializedName("storageState")
  private StorageStateConfiguration storageStateConfiguration;
}
