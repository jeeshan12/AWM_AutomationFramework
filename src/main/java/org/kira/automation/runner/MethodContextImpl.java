package org.kira.automation.runner;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.reflect.Method;
import org.json.JSONObject;
import org.kira.automation.configuration.api.GraphQLQuery;
import org.openqa.selenium.WebDriver;

public class MethodContextImpl implements MethodContext {

  final Method method;
  private WebDriver webDriver;
  private ExtentTest extentTest;
  private RequestSpecification requestSpecification;
  private ResponseSpecification responseSpecification;
  private RequestSpecBuilder requestSpecBuilder;
  private ResponseSpecBuilder responseSpecBuilder;

  private GraphQLQuery graphQLQuery;

  private JSONObject graphQlObject;

  public MethodContextImpl(final Method method) {
    this.method = method;
  }

  @Override
  public WebDriver getWebDriver() {
    return this.webDriver;
  }

  public void setWebDriver(final WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Override
  public ExtentTest getTest() {
    return this.extentTest;
  }

  @Override
  public void flushReport() {
    this.extentTest.getExtent().flush();
  }

  @Override
  public RequestSpecification getRequestSpecification() {
    return this.requestSpecification;
  }

  public void setRequestSpecification(final RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
  }

  @Override
  public ResponseSpecification getResponseSpecification() {
    return this.responseSpecification;
  }

  public void setResponseSpecification(final ResponseSpecification responseSpecification) {
    this.responseSpecification = responseSpecification;
  }

  @Override
  public RequestSpecBuilder getRequestSpecBuilder() {
    return this.requestSpecBuilder;
  }

  public void setRequestSpecBuilder(final RequestSpecBuilder requestSpecBuilder) {
    this.requestSpecBuilder = requestSpecBuilder;
  }

  @Override
  public ResponseSpecBuilder getResponseSpecBuilder() {
    return this.responseSpecBuilder;
  }

  public void setResponseSpecBuilder(final ResponseSpecBuilder responseSpecBuilder) {
    this.responseSpecBuilder = responseSpecBuilder;
  }

  @Override
  public GraphQLQuery getGraphQlQueryInstance() {
    return this.graphQLQuery;
  }

  @Override
  public JSONObject getGraphQlQueryVariablesObject() {
    return this.graphQlObject;
  }

  public void setExtentTest(final ExtentTest extentTest) {
    this.extentTest = extentTest;
  }

  public void setGraphQLQuery(GraphQLQuery graphQLQuery) {
    this.graphQLQuery = graphQLQuery;
  }

  public void setGraphQlVariablesObject(JSONObject graphQlObject) {
    this.graphQlObject = graphQlObject;
  }
}
