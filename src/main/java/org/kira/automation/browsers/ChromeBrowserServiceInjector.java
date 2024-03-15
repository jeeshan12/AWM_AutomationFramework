package org.kira.automation.browsers;

import com.google.inject.AbstractModule;

public class ChromeBrowserServiceInjector extends AbstractModule {

  @Override
  protected void configure() {

    bind(WebDriverService.class).to(ChromeBrowserDriverServiceImpl.class);

  }

}
