package org.kira.automation.runner;

import java.lang.reflect.Method;
import java.util.Map;

public interface CapabilityBuilder {
  Map<String, String> buildCapabilities(Method method);
}
