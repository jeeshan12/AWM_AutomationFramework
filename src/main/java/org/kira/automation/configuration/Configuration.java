package org.kira.automation.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kira.automation.configuration.api.ApiConfiguration;
import org.kira.automation.configuration.mobile.MobileConfiguration;
import org.kira.automation.configuration.web.WebConfiguration;

@ToString
@AllArgsConstructor
@Getter
public class Configuration {

    private WebConfiguration web;

    private MobileConfiguration mobile;

    private ApiConfiguration api;

    private ReportConfiguration report;
}
