package org.kira.automation.configuration.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class WebConfiguration {

    private ScreenshotConfiguration screenshot;
    private String                  browser;
    private boolean                 isHeadless;
    private BrowserOptionsConfig    browserOptions;

}
