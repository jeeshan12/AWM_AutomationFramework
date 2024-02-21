package org.kira.automation.runner;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import redis.clients.jedis.Jedis;

public class MethodContextImpl implements MethodContext {

    final       Method       method;
    private     WebDriver    webDriver;
    private ExtentTest extentTest;
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private RequestSpecBuilder requestSpecBuilder;
    private ResponseSpecBuilder responseSpecBuilder;

    private Jedis jedis;

    public MethodContextImpl(final Method method) {
        this.method = method;
    }
    @Override
    public WebDriver getWebDriver () {
        return this.webDriver;
    }
    @Override
    public ExtentTest getTest () {
        return this.extentTest;
    }
    @Override
    public void flushReport () {
        this.extentTest.getExtent ().flush ();
    }
    @Override
    public RequestSpecification getRequestSpecification () {
        return this.requestSpecification;
    }
    public void setRequestSpecification (final RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }
    public void setResponseSpecification (final ResponseSpecification responseSpecification) {
        this.responseSpecification = responseSpecification;
    }
    public void setRequestSpecBuilder (final RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
    }

    public void setResponseSpecBuilder (final ResponseSpecBuilder responseSpecBuilder) {
        this.responseSpecBuilder = responseSpecBuilder;
    }

    @Override
    public ResponseSpecification getResponseSpecification () {
        return this.responseSpecification;
    }
    @Override
    public RequestSpecBuilder getRequestSpecBuilder () {
        return this.requestSpecBuilder;
    }
    @Override
    public ResponseSpecBuilder getResponseSpecBuilder () {
        return this.responseSpecBuilder;
    }

    @Override
    public Jedis getJedisConnection() {
        return null;
    }

    public void setWebDriver (final WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public  void setExtentTest(final ExtentTest extentTest) {
        this.extentTest = extentTest;
    }
}
