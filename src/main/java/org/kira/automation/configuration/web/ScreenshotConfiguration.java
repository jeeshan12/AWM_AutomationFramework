package org.kira.automation.configuration.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ScreenshotConfiguration {

    private boolean enabled;
    private String path;
}