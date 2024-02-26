package org.kira.automation.browsers;

import com.google.inject.AbstractModule;

public class FirefoxBrowserServiceInjector extends AbstractModule {

  @Override
  protected void configure() {

    bind(BrowserDriverService.class).to(FirefoxBrowserDriverServiceImpl.class);

  }
}
