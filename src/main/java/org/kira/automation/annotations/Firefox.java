package org.kira.automation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Using this annotation with {@link org.testng.annotations.Test} will receive a
 * {@link org.openqa.selenium.WebDriver} instance of
 * {@link org.openqa.selenium.firefox.FirefoxDriver}.
 *
 * @author Mohammad Jeeshan
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Firefox {
}
