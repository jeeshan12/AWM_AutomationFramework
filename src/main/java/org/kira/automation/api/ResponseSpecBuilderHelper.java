package org.kira.automation.api;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderHelper {
    private ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();

    public ResponseSpecBuilderHelper expectStatusCode(int statusCode) {
        specBuilder.expectStatusCode(statusCode);
        return this;
    }

    public ResponseSpecification build() {
        return specBuilder.build();
    }
}
