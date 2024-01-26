package org.kira.automation.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "create")
public class UserRequest {
    private String name;
    private String job;
}
