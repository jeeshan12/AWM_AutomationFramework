package org.kira.automation.connector;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {

  private RedisConnector() {}

  public static RedisConnector getInstance() {
    return RedisConnectorHelper.REDIS_CONNECTOR;
  }

  public Jedis getRedisConnection(final String url, final int port) {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    try (JedisPool jedisPool = new JedisPool(poolConfig, url, port)) {
      return jedisPool.getResource();
    }
  }

  private static class RedisConnectorHelper {

    private static final RedisConnector REDIS_CONNECTOR = new RedisConnector();
  }
}
