package org.kira.automation.service;

import io.restassured.builder.RequestSpecBuilder;
import org.kira.automation.client.RickAndMortyClient;
import org.kira.automation.configuration.api.GraphQLQuery;
import org.kira.automation.model.response.graphql.EpisodeDetails;

public class RickAndMortyService {

  private RickAndMortyService() {}


  public static EpisodeDetails getEpisodeDetailsWithStatusOkAndReturnEpisodeDetails(
      final RequestSpecBuilder requestSpecBuilder, final GraphQLQuery graphQLQuery, final int statusCode) {
    return RickAndMortyClient.getEpisodeDetailsWithStatusOkAndReturnEpisodeDetails(
        requestSpecBuilder, graphQLQuery, statusCode
    );
  }
}
