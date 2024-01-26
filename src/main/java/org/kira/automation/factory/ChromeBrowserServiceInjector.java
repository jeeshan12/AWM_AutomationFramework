package org.kira.automation.factory;

public class ChromeBrowserServiceInjector implements BrowserDriverServiceInjector{
  @Override public BrowserConsumer getBrowserConsumer() {
    return new BrowserConsumerImpl(new ChromeBrowserDriverServiceImpl());
  }
}
