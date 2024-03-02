package org.kira.automation.runner;

import org.kira.automation.connector.RedisManager;

public interface SuiteContext {
  RedisManager getRedisManager();
}
