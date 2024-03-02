package org.kira.automation.connector;

import java.util.Set;
import redis.clients.jedis.Jedis;

public class RedisManager {

  private  final String stateKey;

  private final RedisConnector redisConnector;

  private final String url;

  private final int port;

  public RedisManager(String url, int port, String stateKey) {
    this.redisConnector = RedisConnector.getInstance();
    this.url = url;
    this.port = port;
    this.stateKey = stateKey;
  }

  public void storeState(Set<String> state) {
    try (Jedis jedis = redisConnector.getRedisConnection(this.url, this.port)) {
      if (!Boolean.TRUE.equals(jedis.exists(this.stateKey))) {
        jedis.sadd(this.stateKey, state.toArray(new String[0]));
      }
    }
  }

  public Set<String> retrieveState() {
    try (Jedis jedis = redisConnector.getRedisConnection(this.url, this.port)) {
      return jedis.smembers(this.stateKey);
    }
  }

  public void clearState() {
    try (Jedis jedis = redisConnector.getRedisConnection(this.url, this.port)) {
      jedis.del(this.stateKey);
    }
  }
}