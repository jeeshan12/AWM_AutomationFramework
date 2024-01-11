package org.kira.automation.configuration.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class ApiConfiguration {
    private String baseurl;
    private ApiLoggingConfiguration log;
    private int responseTimeOut;

}
