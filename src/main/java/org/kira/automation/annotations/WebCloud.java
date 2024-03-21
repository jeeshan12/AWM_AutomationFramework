package org.kira.automation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebCloud {
  String os() default "Windows";

  String osVersion() default "11";

  String browserVersion() default "latest";

  String resolution() default "1024x768";

  String browserName() default "chrome";
}
