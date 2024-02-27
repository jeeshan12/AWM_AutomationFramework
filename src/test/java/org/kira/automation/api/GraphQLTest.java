package org.kira.automation.api;

import static com.aventstack.extentreports.Status.INFO;
import static org.apache.http.HttpStatus.SC_OK;
import static org.kira.automation.constants.FrameworkConstants.GRAPHQL_QUERY_PATH;

import org.assertj.core.api.SoftAssertions;
import org.kira.automation.annotations.Api;
import org.kira.automation.model.request.graphql.EpisodeId;
import org.kira.automation.model.response.graphql.EpisodeDetails;
import org.kira.automation.model.response.graphql.EpisodeDetails.Episode;
import org.kira.automation.runner.TestSuiteRunner;
import org.kira.automation.service.RickAndMortyService;
import org.kira.automation.utils.FileUtils;
import org.testng.annotations.Test;

public class GraphQLTest extends TestSuiteRunner {

  @Test
  @Api
  public void testGraphQlQuery() {

    getExtentTest().log(INFO, "Creating the query");

    getGraphQlQueryInstance().setQuery(FileUtils.readFileAsString(
        GRAPHQL_QUERY_PATH + "episode.gql"
    ));
    getExtentTest().log(INFO, "Creating the query variables");
    getGraphQlQueryVariablesObject().put("episodeId", "3");
    getGraphQlQueryInstance().setVariables(
        EpisodeId.create().episodeId("3").build()
    );
    getExtentTest().log(INFO, "Sending the request for getting episode detail");
    EpisodeDetails episodeDetails = RickAndMortyService.getEpisodeDetailsWithStatusOkAndReturnEpisodeDetails(
        getRequestSpecBuilder(), getGraphQlQueryInstance(), SC_OK
    );

    Episode episode = episodeDetails.getData().getEpisode();

    getExtentTest().log(INFO, "Asserting GraphQL response");

    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(episode.getAirDate()).as("Episode Air Date").isEqualTo("December 16, 2013");
      softly.assertThat(episode.getCreated()).as("Episode Created Date")
          .isEqualTo("2017-11-10T12:56:34.022Z");
      softly.assertThat(episode.getEpisode()).as("Episode Number").isEqualTo("S01E03");
      softly.assertThat(episode.getId()).as("Episode Id").isEqualTo("3");
      softly.assertThat(episode.getName()).as("Episode Name").isEqualTo("Anatomy Park");
    });
  }
}
