package org.kira.automation.drivers.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.drivers.DriverConsumer;
import org.kira.automation.enums.Mobile;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.kira.automation.runner.MethodContextImpl;

public class MobileDriverFactory {

  private MobileDriverFactory() {
  }

  private static final Map<String, Class<? extends AbstractModule>> INJECTOR_MAP = new HashMap<>();

  static {
    INJECTOR_MAP.put(Mobile.ANDROID.getName(), AndroidDriverServiceInjector.class);
    INJECTOR_MAP.put(Mobile.IOS.getName(), IosDriverServiceInjector.class);
  }

  @SneakyThrows
  public static void addMobileDriver(MethodContextImpl context, Configuration configuration,
      String platform) {
    Class<? extends AbstractModule> injectorClass = INJECTOR_MAP.get(platform);
    if (injectorClass != null) {
      Constructor<? extends AbstractModule> constructor = injectorClass.getConstructor();
      AbstractModule abstractModule = constructor.newInstance();
      Injector driverServiceInjector = Guice.createInjector(abstractModule);
      DriverConsumer driverConsumer = driverServiceInjector.getInstance(DriverConsumer.class);
      context.setWebDriver(driverConsumer.getWebDriver(configuration));
    } else {
      throw new FrameworkGenericException("Unsupported mobile platform: " + platform);
    }
  }
}
