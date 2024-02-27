package org.kira.automation.configuration.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.kira.automation.api.ServiceConfiguration;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GraphQlConfiguration implements ServiceConfiguration {
  private String baseurl;
  private String basePath;
  private String queryPath;
}
