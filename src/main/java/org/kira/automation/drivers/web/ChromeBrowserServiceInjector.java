package org.kira.automation.drivers.web;

import com.google.inject.AbstractModule;
import org.kira.automation.drivers.WebDriverService;

public class ChromeBrowserServiceInjector extends AbstractModule {

  @Override
  protected void configure() {
    bind(WebDriverService.class).to(ChromeBrowserDriverServiceImpl.class);
  }

}
