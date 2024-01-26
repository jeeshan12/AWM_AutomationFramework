package org.kira.automation.factory;

import com.google.inject.AbstractModule;

public class ChromeBrowserServiceInjector extends AbstractModule {
  @Override
  protected void configure() {

    bind(BrowserDriverService.class).to(ChromeBrowserDriverServiceImpl.class);

  }

}
