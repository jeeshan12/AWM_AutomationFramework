package org.kira.automation;

import java.lang.reflect.InvocationTargetException;
import org.kira.automation.connector.RedisConnector;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertThrows;

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
  public void testPreventReflection() {
    assertThrows(InvocationTargetException.class, () -> {
      RedisConnector redisConnector = RedisConnector.getInstance();
      java.lang.reflect.Constructor<RedisConnector> constructor =
          RedisConnector.class.getDeclaredConstructor();
      constructor.setAccessible(true);
      constructor.newInstance();
    });
  }
}
