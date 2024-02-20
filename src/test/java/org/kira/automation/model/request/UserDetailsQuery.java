package org.kira.automation.model.request;

import io.restassured.builder.RequestSpecBuilder;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserDetailsQuery {

  static final Function<String, Consumer<RequestSpecBuilder>> USER_QUERY_CONSUMER_FUNCTION = (page) -> {
    return (RequestSpecBuilder specBuilder) -> {
      specBuilder.addQueryParam("page", page);
    };
  };

  public static Consumer<RequestSpecBuilder> createUserQuery(String page) {
    return USER_QUERY_CONSUMER_FUNCTION.apply(page);
  }
}
