package org.kira.automation.factory;

public class FirefoxBrowserServiceInjector implements BrowserDriverServiceInjector{
  @Override public BrowserConsumer getBrowserConsumer() {
    return new BrowserConsumerImpl(new FirefoxBrowserDriverServiceImpl());
  }
}
