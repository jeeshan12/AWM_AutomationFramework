package org.kira.automation.runner;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.reflect.Method;
import lombok.Setter;
import org.json.JSONObject;
import org.kira.automation.configuration.api.GraphQLQuery;
import org.openqa.selenium.WebDriver;

public class MethodContextImpl implements MethodContext {

  final Method method;

  @Setter
  private WebDriver webDriver;

  @Setter
  private ExtentTest extentTest;

  @Setter
  private RequestSpecification requestSpecification;

  @Setter
  private ResponseSpecification responseSpecification;

  @Setter
  private RequestSpecBuilder requestSpecBuilder;

  @Setter
  private ResponseSpecBuilder responseSpecBuilder;

  @Setter
  private GraphQLQuery graphQLQuery;

  private JSONObject graphQlObject;

  public MethodContextImpl(final Method method) {
    this.method = method;
  }

  @Override
  public WebDriver getWebDriver() {
    return this.webDriver;
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

  @Override
  public ResponseSpecification getResponseSpecification() {
    return this.responseSpecification;
  }

  @Override
  public RequestSpecBuilder getRequestSpecBuilder() {
    return this.requestSpecBuilder;
  }

  @Override
  public ResponseSpecBuilder getResponseSpecBuilder() {
    return this.responseSpecBuilder;
  }

  @Override
  public GraphQLQuery getGraphQlQueryInstance() {
    return this.graphQLQuery;
  }

  @Override
  public JSONObject getGraphQlQueryVariablesObject() {
    return this.graphQlObject;
  }

  public void setGraphQlVariablesObject(JSONObject graphQlObject) {
    this.graphQlObject = graphQlObject;
  }
}
