package org.kira.automation.drivers.mobile;

import com.google.inject.AbstractModule;
import org.kira.automation.drivers.WebDriverService;

public class AndroidDriverServiceInjector extends AbstractModule {

  @Override
  protected void configure() {
    bind(WebDriverService.class).to(AndroidDriverServiceImpl.class);
  }
}
