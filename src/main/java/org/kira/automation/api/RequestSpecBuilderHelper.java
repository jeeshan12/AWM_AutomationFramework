package org.kira.automation.api;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderHelper {
    private RequestSpecBuilder specBuilder = new RequestSpecBuilder();

    public RequestSpecBuilderHelper setBaseUri(String baseUri) {
        specBuilder.setBaseUri(baseUri);
        return this;
    }

    public RequestSpecBuilderHelper addHeaders(Map<String, String> headers) {
        specBuilder.addHeaders(headers);
        return this;
    }

    public RequestSpecBuilderHelper enableLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return this;
    }

    public RequestSpecification build() {
        return specBuilder.build();
    }
}
