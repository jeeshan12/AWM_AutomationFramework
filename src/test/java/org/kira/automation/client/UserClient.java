package org.kira.automation.client;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import org.kira.automation.model.request.UserRequest;
import org.kira.automation.model.response.UserDetails;
import org.kira.automation.model.response.UserResponse;

public class UserClient {

  private static final String USER_ENDPOINT = "/users";

  public static UserResponse createUserWithStatusCreatedAndReturnResponse(
      final RequestSpecBuilder requestSpecBuilder, final UserRequest userRequest, final
  int httpStatus) {
    return given().spec(requestSpecBuilder.build())
        .body(userRequest).post(USER_ENDPOINT)
        .then()
        .statusCode(httpStatus).extract()
        .response().as(UserResponse.class);
  }


  public static UserDetails getUserDetailsWithStatusOkAndReturnUserDetailsResponse(
      final RequestSpecBuilder requestSpecBuilder, String page, final
  int httpStatus) {
    return given().spec(requestSpecBuilder.build())
        .get(USER_ENDPOINT)
        .then().statusCode(httpStatus)
        .extract()
        .response().as(UserDetails.class);
  }
}
