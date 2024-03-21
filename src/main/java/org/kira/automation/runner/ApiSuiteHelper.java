package org.kira.automation.runner;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.lang.reflect.Method;
import org.json.JSONObject;
import org.kira.automation.annotations.Api;
import org.kira.automation.api.ServiceConfiguration;
import org.kira.automation.api.ServiceConfigurationFactory;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.api.GraphQLQuery;
import org.kira.automation.configuration.api.GraphQlConfiguration;

public class ApiSuiteHelper {

  private ApiSuiteHelper() {}

  static void setUpApiConfig(final MethodContextImpl context, final Configuration configuration) {
    Method method = context.method;
    if (!method.isAnnotationPresent(Api.class)) {
      return;
    }
    ServiceConfiguration serviceConfiguration = ServiceConfigurationFactory.createConfiguration(
      configuration.getApi()
    );
    if (serviceConfiguration instanceof GraphQlConfiguration) {
      setUpGraphQlConfig(context);
    }
    RequestSpecBuilder requestSpecBuilder = getRequestSpecBuilder(serviceConfiguration);
    context.setRequestSpecBuilder(requestSpecBuilder);
    context.setRequestSpecification(requestSpecBuilder.build());
    ResponseSpecBuilder responseSpecBuilder = getResponseSpecBuilder();
    context.setResponseSpecBuilder(responseSpecBuilder);
    context.setResponseSpecification(responseSpecBuilder.build());
  }

  private static void setUpGraphQlConfig(MethodContextImpl context) {
    context.setGraphQLQuery(new GraphQLQuery());
    context.setGraphQlVariablesObject(new JSONObject());
  }

  private static RequestSpecBuilder getRequestSpecBuilder(
    final ServiceConfiguration serviceConfiguration
  ) {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    return requestSpecBuilder
      .setBaseUri(serviceConfiguration.getBaseurl())
      .setBasePath(serviceConfiguration.getBasePath())
      .setContentType(ContentType.JSON);
  }

  private static ResponseSpecBuilder getResponseSpecBuilder() {
    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
    return responseSpecBuilder.log(LogDetail.ALL);
  }
}
