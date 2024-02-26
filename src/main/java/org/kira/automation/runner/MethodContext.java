package org.kira.automation.runner;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.WebDriver;
import redis.clients.jedis.Jedis;

public interface MethodContext {

  WebDriver getWebDriver();

  ExtentTest getTest();

  void flushReport();

  RequestSpecification getRequestSpecification();

  ResponseSpecification getResponseSpecification();

  RequestSpecBuilder getRequestSpecBuilder();

  ResponseSpecBuilder getResponseSpecBuilder();

  Jedis getJedisConnection();

}
