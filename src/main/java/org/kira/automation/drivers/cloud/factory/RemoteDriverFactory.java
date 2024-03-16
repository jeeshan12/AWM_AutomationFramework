package org.kira.automation.drivers.cloud.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.kira.automation.drivers.cloud.BrowserstackRemoteDriverServiceImpl;
import org.kira.automation.drivers.cloud.CloudRemoteDriverService;
import org.kira.automation.drivers.cloud.LambdatestRemoteDriverServiceImpl;
import org.kira.automation.enums.CloudProvider;

public class RemoteDriverFactory {

  private static final Supplier<CloudRemoteDriverService> BROWSERSTACK_REMOTE_DRIVER_SERVICE_SUPPLIER = BrowserstackRemoteDriverServiceImpl::new;
  private static final Supplier<CloudRemoteDriverService> LAMBDATEST_REMOTE_DRIVER_SERVICE_SUPPLIER = LambdatestRemoteDriverServiceImpl::new;
  private static final Map<String, Supplier<CloudRemoteDriverService>> REMOTE_DRIVER_MAP = new HashMap<>();

  static {
    REMOTE_DRIVER_MAP.put(CloudProvider.BROWSERSTACK.getProviderName(),
        BROWSERSTACK_REMOTE_DRIVER_SERVICE_SUPPLIER);
    REMOTE_DRIVER_MAP.put(CloudProvider.LAMBDATEST.getProviderName(),
        LAMBDATEST_REMOTE_DRIVER_SERVICE_SUPPLIER);

  }

  private RemoteDriverFactory() {
  }

  public static CloudRemoteDriverService getRemoteWebDriver(String cloudPlatform) {
    return REMOTE_DRIVER_MAP.get(cloudPlatform).get();
  }
}


