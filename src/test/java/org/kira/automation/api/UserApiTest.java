package org.kira.automation.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.kira.automation.annotations.Api;
import org.kira.automation.model.request.UserDetailsQuery;
import org.kira.automation.model.request.UserRequest;
import org.kira.automation.model.response.UserDetails;
import org.kira.automation.model.response.UserResponse;
import org.kira.automation.runner.TestSuiteRunner;
import org.kira.automation.service.UserService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserApiTest extends TestSuiteRunner {


  private Faker faker;


  @BeforeMethod
  public void setup() {
    this.faker = new Faker();
  }

  @Test
  @Api
  public void createUser() {

    var userRequest = UserRequest.create()
        .name(this.faker.name().fullName())
        .job(this.faker.job().field())
        .build();

    UserResponse userResponse =
        UserService.createUserWithStatusCreatedAndReturnResponse(getRequestSpecBuilder(),
            userRequest,
            HttpStatus.SC_CREATED
        );

    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(userResponse.getName()).as("User name").isEqualTo(userRequest.getName());
      softly.assertThat(userResponse.getJob()).as("User Job").isEqualTo(userRequest.getJob());
      softly.assertThat(userResponse.getId()).as("User Id").isNotEmpty();
      softly.assertThat(userResponse.getCreatedAt()).as("User Creation date").isNotEmpty();
    });
  }


  @Test
  @Api
  public void getUserDetails() {
    UserDetailsQuery.createUserQuery("2").accept(getRequestSpecBuilder());

    UserDetails userDetails =
        UserService.getUserDetailsWithStatusOkAndReturnUserDetailsResponse(getRequestSpecBuilder(),
            "2",
            HttpStatus.SC_OK
        );

    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(userDetails.getPage()).as("Page").isEqualTo(2);
      softly.assertThat(userDetails.getPerPage()).as("User Details per page").isEqualTo(6);
      softly.assertThat(userDetails.getTotal()).as("Total User Details").isEqualTo(12);
      softly.assertThat(userDetails.getTotalPages()).as("Total Pages").isEqualTo(2);
    });
    assertThat(userDetails.getData()).hasSize(6);
  }


}
