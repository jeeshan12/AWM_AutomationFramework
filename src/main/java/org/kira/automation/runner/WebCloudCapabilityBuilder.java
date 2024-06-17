package org.kira.automation.runner;

import static org.kira.automation.constants.FrameworkConstants.BROWSER_NAME;
import static org.kira.automation.constants.FrameworkConstants.BROWSER_VERSION;
import static org.kira.automation.constants.FrameworkConstants.OS;
import static org.kira.automation.constants.FrameworkConstants.OS_VERSION;
import static org.kira.automation.constants.FrameworkConstants.RESOLUTION;

import java.lang.reflect.Method;
import java.util.Map;
import org.kira.automation.annotations.WebCloud;

public class WebCloudCapabilityBuilder implements CapabilityBuilder {

  @Override
  public Map<String, String> buildCapabilities(Method method) {
    WebCloud webCloudAnnotation = method.getAnnotation(WebCloud.class);
    return Map.of(
      BROWSER_VERSION,
      webCloudAnnotation.browserVersion(),
      OS,
      webCloudAnnotation.os(),
      OS_VERSION,
      webCloudAnnotation.osVersion(),
      BROWSER_NAME,
      webCloudAnnotation.browserName(),
      RESOLUTION,
      webCloudAnnotation.resolution()
    );
  }
}
