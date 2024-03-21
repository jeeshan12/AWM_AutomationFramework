package org.kira.automation.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.kira.automation.configuration.api.ApiConfiguration;
import org.kira.automation.enums.Backend;
import org.kira.automation.exceptions.FrameworkGenericException;

public class ServiceConfigurationFactory {

  private ServiceConfigurationFactory() {}

  private static final Map<
    String,
    Function<ApiConfiguration, ServiceConfiguration>
  > configurationMap = new HashMap<>();

  static {
    configurationMap.put(Backend.REST.getApi(), ApiConfiguration::getRestConfiguration);
    configurationMap.put(Backend.GRAPHQL.getApi(), ApiConfiguration::getGraphQLConfiguration);
  }

  public static ServiceConfiguration createConfiguration(ApiConfiguration apiConfiguration) {
    String backend = apiConfiguration.getBackend();
    Function<ApiConfiguration, ServiceConfiguration> configurationFunction = configurationMap.get(
      backend
    );

    if (configurationFunction == null) {
      throw new FrameworkGenericException("Unsupported backend: " + backend);
    }

    return configurationFunction.apply(apiConfiguration);
  }
}
