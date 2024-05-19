package org.kira.automation.utils;

import java.util.function.Supplier;

public class LazyFieldInitializer<T> {

  private final Supplier<T> supplier;
  private T instance;

  public LazyFieldInitializer(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  public T get() {
    if (instance == null) {
      synchronized (this) {
        if (instance == null) {
          instance = supplier.get();
        }
      }
    }
    return instance;
  }
}
