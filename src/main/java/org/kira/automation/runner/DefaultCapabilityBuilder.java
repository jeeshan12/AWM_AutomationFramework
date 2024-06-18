package org.kira.automation.runner;

import static java.util.Collections.emptyMap;

import java.lang.reflect.Method;
import java.util.Map;

public class DefaultCapabilityBuilder implements CapabilityBuilder {

  @Override
  public Map<String, String> buildCapabilities(Method method) {
    return emptyMap();
  }
}
