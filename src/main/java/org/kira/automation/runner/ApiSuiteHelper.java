package org.kira.automation.runner;

import java.lang.reflect.Method;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.kira.automation.annotations.Api;
import org.kira.automation.configuration.Configuration;
import org.kira.automation.configuration.api.ApiConfiguration;

public class ApiSuiteHelper {

    private ApiSuiteHelper() {}

    static void setUpApiConfig (final MethodContextImpl context, final Configuration configuration) {
        Method method = context.method;
        if (!method.isAnnotationPresent (Api.class)) return;
        RequestSpecBuilder requestSpecBuilder = getRequestSpecBuilder(configuration.getApi ());
        context.setRequestSpecBuilder (requestSpecBuilder);
        context.setRequestSpecification (requestSpecBuilder.build ());
        ResponseSpecBuilder responseSpecBuilder = getResponseSpecBuilder (configuration.getApi ());
        context.setResponseSpecBuilder (responseSpecBuilder);
        context.setResponseSpecification (responseSpecBuilder.build ());
    }

    private static RequestSpecBuilder getRequestSpecBuilder (final ApiConfiguration apiConfiguration) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder ();
        return requestSpecBuilder
            .setBaseUri (apiConfiguration.getBaseurl ())
            .setBasePath (apiConfiguration.getBasePath ())
            .setContentType (ContentType.JSON)
            .log (LogDetail.BODY);
    }

    private static ResponseSpecBuilder getResponseSpecBuilder (final ApiConfiguration apiConfiguration) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder ();
        return responseSpecBuilder.log (LogDetail.ALL);
    }
}
