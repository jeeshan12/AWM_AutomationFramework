package org.kira.automation.model.response.graphql;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class EpisodeDetails {

  private Data data;

  @Getter
  @AllArgsConstructor
  @ToString
  public static class Data {

    private Episode episode;
  }

  @Getter
  @AllArgsConstructor
  @ToString
  public static class Episode {

    @SerializedName("air_date")
    private String airDate;

    private String created;

    private String episode;

    private String name;

    private String id;
  }
}
