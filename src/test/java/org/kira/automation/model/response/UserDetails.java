package org.kira.automation.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

  private int page;

  @SerializedName("per_page")
  private int perPage;

  private int total;

  @SerializedName("total_pages")
  private int totalPages;

  private List<User> data;

  private Support support;
}
