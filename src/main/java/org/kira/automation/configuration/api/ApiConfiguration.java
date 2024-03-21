package org.kira.automation.configuration.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class ApiConfiguration {

  private ApiLoggingConfiguration log;

  private int responseTimeOut;

  @SerializedName("graphql")
  private GraphQlConfiguration graphQLConfiguration;

  @SerializedName("rest")
  private RestConfiguration restConfiguration;

  private String backend;
}
