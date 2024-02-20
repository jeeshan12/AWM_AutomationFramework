package org.kira.automation.service;

import io.restassured.builder.RequestSpecBuilder;
import org.kira.automation.client.UserClient;
import org.kira.automation.model.request.UserRequest;
import org.kira.automation.model.response.UserResponse;

public class UserService {


  private UserService() {}

  public static UserResponse createUserWithStatusCreatedAndReturnResponse(final RequestSpecBuilder requestSpecBuilder, final UserRequest userRequest, final int statusCode) {
      return UserClient.createUserWithStatusCreatedAndReturnResponse(requestSpecBuilder, userRequest, statusCode);
  }
}
