package org.kira.automation.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.kira.automation.model.request.UserRequest;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends UserRequest {
    private String id;
    private String createdAt;
}
