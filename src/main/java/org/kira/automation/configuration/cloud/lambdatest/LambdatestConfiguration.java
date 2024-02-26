package org.kira.automation.configuration.cloud.lambdatest;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.cloud.CloudBrowserConfig;

@ToString
@AllArgsConstructor
@Getter
public class LambdatestConfiguration {

    private String buildName;

    private String projectName;

    private String userName;

    private String accessKey;

    private String browserName;

    private String console;

    private String networkThrottling;

    private String timezone;

    private boolean network;

    private boolean headless;

    private boolean video;

    private boolean visual;

    private String seleniumVersion;

    private boolean tunnel;

    @SerializedName("chrome")
    private CloudBrowserConfig chromeConfig;

    @SerializedName("firefox")
    private CloudBrowserConfig firefoxConfig;

    @SerializedName("safari")
    private CloudBrowserConfig safariConfig;

    @SerializedName("edge")
    private CloudBrowserConfig edgeConfig;

    @SerializedName("ie")
    private CloudBrowserConfig ieConfig;
}
