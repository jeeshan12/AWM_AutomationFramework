package org.kira.automation.framework;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;
import org.kira.automation.connector.RedisConnector;
import org.kira.automation.connector.RedisManager;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

public class RedisManagerTest {

  @Mock
  private RedisConnector redisConnectorMock;

  @Mock
  private Jedis jedisMock;

  private RedisManager redisManager;

  private String stateKey = "test_key";

  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    int port = 6379;
    String url = "localhost";
    when(this.redisConnectorMock.getRedisConnection(url, port)).thenReturn(jedisMock);
    this.redisManager = new RedisManager(url, port, stateKey, redisConnectorMock);
  }

  @Test
  public void testStoreState() {
    Set<String> sampleState = new HashSet<>();
    sampleState.add("sample_state1");
    sampleState.add("sample_state2");

    redisManager.storeState(sampleState);
    verify(jedisMock, times(1)).sadd(stateKey, "sample_state1", "sample_state2");
  }

  @Test
  public void testRetrieveState() {
    Set<String> mockState = new HashSet<>();
    mockState.add("value1");
    mockState.add("value2");
    when(jedisMock.smembers(stateKey)).thenReturn(mockState);

    Set<String> retrievedState = redisManager.retrieveState();

    assertEquals(mockState, retrievedState);
  }

  @Test
  public void testClearState() {
    redisManager.clearState();

    verify(jedisMock, times(1)).del(stateKey);
  }

  @Test
  public void testStoreStateWhenKeyExists() {
    Set<String> state = new HashSet<>();
    state.add("value1");
    state.add("value2");

    // Mock Jedis response
    when(jedisMock.exists(stateKey)).thenReturn(true);

    redisManager.storeState(state);

    // Verify that sadd is not called
    verify(jedisMock, times(1)).exists(stateKey);
    verify(jedisMock, never()).sadd(stateKey, "value1", "value2");
  }

  @Test
  public void testStoreStateWithException() {
    Set<String> state = new HashSet<>();
    state.add("value1");
    state.add("value2");

    // Mock Jedis response
    when(jedisMock.exists(stateKey)).thenThrow(new JedisException("Connection error"));

    try {
      redisManager.storeState(state);
    } catch (JedisException e) {
      // Exception expected
    }
    verify(jedisMock, times(1)).exists(stateKey);
    verify(jedisMock, never()).sadd(stateKey, "value1", "value2");
  }

  @Test
  public void testRetrieveStateWithException() {
    // Mock Jedis response
    when(jedisMock.smembers(stateKey)).thenThrow(new JedisException("Connection error"));

    Set<String> retrievedState = null;
    try {
      retrievedState = redisManager.retrieveState();
    } catch (JedisException e) {
      // Exception expected
    }

    // Verify result
    assertNull(retrievedState);
  }

  @Test
  public void testClearStateWithException() {
    doThrow(new JedisException("Connection error")).when(jedisMock).del(stateKey);

    try {
      redisManager.clearState();
    } catch (JedisException e) {
      // Exception expected
    }
    verify(jedisMock, times(1)).del(stateKey);
  }
}
