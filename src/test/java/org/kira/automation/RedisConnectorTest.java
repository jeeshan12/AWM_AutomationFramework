package org.kira.automation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.kira.automation.connector.RedisConnector;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

public class RedisConnectorTest {

  @Test
  public void testRedisInstance() {
    RedisConnector redisConnector1 = RedisConnector.getInstance();
    RedisConnector redisConnector2 = RedisConnector.getInstance();

    assertThat(redisConnector1).isNotNull();
    assertThat(redisConnector2).isNotNull();
    assertThat(redisConnector1).isEqualTo(redisConnector2);
  }

  @Test
  public void testRedisConnection() {
    RedisConnector redisConnector = RedisConnector.getInstance();
    Jedis jedis = redisConnector.getRedisConnection("localhost", 6379);
    assertThat(jedis).isNotNull();
  }

  @Test
  public void testReflectionSingletonViolation()
    throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<RedisConnector> constructor = RedisConnector.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}
