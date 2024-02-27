package org.kira.automation.model.request.graphql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "create")
public class EpisodeId {

    private String episodeId;

}
