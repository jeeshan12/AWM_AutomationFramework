package org.kira.automation.browsers.cloud;

import org.kira.automation.annotations.WebCloud;
import org.kira.automation.enums.CloudProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RemoteDriverFactory {

    private RemoteDriverFactory() {}

    private static final Supplier<CloudRemoteDriverService> BROWSERSTACK_REMOTE_DRIVER_SERVICE_SUPPLIER = BrowserstackRemoteDriverServiceImpl::new;

    private static final Supplier<CloudRemoteDriverService> LAMBDATEST_REMOTE_DRIVER_SERVICE_SUPPLIER = LambdaTestRemoteDriverServiceImpl::new;


    private static final Map<String, Supplier<CloudRemoteDriverService>> REMOTE_DRIVER_MAP = new HashMap<>();

    static {
        REMOTE_DRIVER_MAP.put(CloudProvider.BROWSERSTACK.getProviderName(), BROWSERSTACK_REMOTE_DRIVER_SERVICE_SUPPLIER);
        REMOTE_DRIVER_MAP.put(CloudProvider.LAMBDATEST.getProviderName(), LAMBDATEST_REMOTE_DRIVER_SERVICE_SUPPLIER);

    }

    public static CloudRemoteDriverService getRemoteWebDriver(String cloudPlatform) {
        return REMOTE_DRIVER_MAP.get(cloudPlatform).get();
    }
}


