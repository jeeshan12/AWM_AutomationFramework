package org.kira.automation.configuration.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GraphQLQuery {

  private String query;
  private Object variables;
}
