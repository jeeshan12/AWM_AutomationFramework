package org.kira.automation.client;

import org.kira.automation.model.request.UserRequest;
import org.kira.automation.model.response.UserResponse;

public class UserClient {

    private static final String USER_ENDPOINT = "/api/users";

    public static UserResponse createUser(final UserRequest userRequest) {
        return new UserResponse ();
    }
}
