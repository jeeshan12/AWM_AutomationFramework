package org.kira.automation.client;

import io.restassured.builder.RequestSpecBuilder;
import org.kira.automation.model.response.UserResponse;

public class UserClient {

    private static final String USER_ENDPOINT = "/users";

    public static UserResponse createUser(final RequestSpecBuilder requestSpecBuilder) {
        return requestSpecBuilder.build().post (USER_ENDPOINT).then ().extract ().response ().as (UserResponse.class);
    }
}
