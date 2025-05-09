package org.camunda.community.mockito.function;

import org.mockito.Mockito;

/**
 * Helper to create either mock() or new() instances for given type.
 */
public final class CreateInstance {

  public static <T> T mockInstance(final Class<T> type) {
    return Mockito.mock(type);
  }

  @SuppressWarnings("unchecked")
  public static <T> T newInstanceByDefaultConstructor(final Class<T> type) {
    try {
      return (T) type.getConstructors()[0].newInstance();
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }

  }
}
