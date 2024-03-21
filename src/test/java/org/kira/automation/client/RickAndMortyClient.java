package org.kira.automation.client;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import org.kira.automation.configuration.api.GraphQLQuery;
import org.kira.automation.model.response.graphql.EpisodeDetails;

public class RickAndMortyClient {

  public static EpisodeDetails getEpisodeDetailsWithStatusOkAndReturnEpisodeDetails(
    final RequestSpecBuilder requestSpecBuilder,
    final GraphQLQuery graphQLQuery,
    final int httpStatus
  ) {
    return given()
      .spec(requestSpecBuilder.build())
      .body(graphQLQuery)
      .post("/")
      .then()
      .statusCode(httpStatus)
      .extract()
      .response()
      .as(EpisodeDetails.class);
  }
}
