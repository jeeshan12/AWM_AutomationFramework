package org.kira.automation.configuration.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class FirefoxOptionsConfig {
    private List<String>   options;
    private DownloadOption downloadOption;

}
