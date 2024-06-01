package org.kira.automation.framework;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.kira.automation.api.ServiceConfiguration;
import org.kira.automation.api.ServiceConfigurationFactory;
import org.kira.automation.configuration.api.ApiConfiguration;
import org.kira.automation.configuration.api.GraphQlConfiguration;
import org.kira.automation.configuration.api.RestConfiguration;
import org.kira.automation.enums.Backend;
import org.kira.automation.exceptions.FrameworkGenericException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ServiceConfigurationFactoryTest {

  @Mock
  private ApiConfiguration apiConfiguration;

  @Mock
  private RestConfiguration restServiceConfiguration;

  @Mock
  private GraphQlConfiguration graphqlServiceConfiguration;

  @BeforeMethod
  void setUp() {
    MockitoAnnotations.openMocks(this);

    when(apiConfiguration.getBackend()).thenReturn(
      Backend.REST.getApi(),
      Backend.GRAPHQL.getApi(),
      "INVALID_BACKEND"
    );
    when(apiConfiguration.getRestConfiguration()).thenReturn(restServiceConfiguration);
    when(apiConfiguration.getGraphQLConfiguration()).thenReturn(graphqlServiceConfiguration);
  }

  @Test
  void testCreateConfigurationForRestBackend() {
    when(apiConfiguration.getBackend()).thenReturn(Backend.REST.getApi());
    ServiceConfiguration serviceConfiguration = ServiceConfigurationFactory.createConfiguration(
      apiConfiguration
    );
    assertNotNull(serviceConfiguration);
    assertEquals(restServiceConfiguration, serviceConfiguration);
  }

  @Test
  void testCreateConfigurationForGraphQLBackend() {
    when(apiConfiguration.getBackend()).thenReturn(Backend.GRAPHQL.getApi());

    ServiceConfiguration serviceConfiguration = ServiceConfigurationFactory.createConfiguration(
      apiConfiguration
    );

    assertNotNull(serviceConfiguration);
    assertEquals(graphqlServiceConfiguration, serviceConfiguration);
  }

  @Test
  public void testCreateConfigurationForInvalidBackend() {
    when(apiConfiguration.getBackend()).thenReturn("INVALID_BACKEND");

    try {
      ServiceConfigurationFactory.createConfiguration(apiConfiguration);
      fail("Expected FrameworkGenericException to be thrown");
    } catch (FrameworkGenericException e) {
      String expectedMessage = "Unsupported backend: INVALID_BACKEND";
      String actualMessage = e.getMessage();
      assertTrue(actualMessage.contains(expectedMessage));
    }
  }
}
