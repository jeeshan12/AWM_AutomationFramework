package org.kira.automation.connector;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {

  private RedisConnector() {
    if (RedisConnectorHelper.REDIS_CONNECTOR != null) {
      throw new IllegalStateException("Singleton instance already exists for Redis connector");
    }
  }

  private static class RedisConnectorHelper {
    private static final RedisConnector REDIS_CONNECTOR = new RedisConnector();
    // If your Redis server requires authentication, you can use REDIS_CONNECTOR.auth("password");
  }

  public static RedisConnector getInstance() {
    return RedisConnectorHelper.REDIS_CONNECTOR;
  }

  public Jedis getRedisConnection(final String url, final int port) {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    JedisPool jedisPool = new JedisPool(poolConfig, url, port);
    return jedisPool.getResource();
  }

}
