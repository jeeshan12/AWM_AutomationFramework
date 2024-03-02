package org.kira.automation.configuration.web;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StorageStateConfiguration {

  @SerializedName("enabled")
  private boolean isStorageStateEnabled;

  @SerializedName("path")
  private boolean storageStatePath;

  @SerializedName("key")
  private String storageStateKey;

  @SerializedName("sessionStorage")
  private boolean isSessionStorageEnabled;

  @SerializedName("url")
  private String redisUrl;

  @SerializedName("port")
  private int redisPort;

  private boolean redisEnabled;

}
