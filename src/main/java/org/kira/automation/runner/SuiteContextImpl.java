package org.kira.automation.runner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kira.automation.connector.RedisManager;

@NoArgsConstructor
@Setter
public class SuiteContextImpl implements SuiteContext{
  private RedisManager redisManager;
  @Override
  public RedisManager getRedisManager() {
    return this.redisManager;
  }

}
