package io.holunda.commons.immutables;

import io.holunda.commons.immutables._JacksonHelper.JsonMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.holunda.commons.immutables._JacksonHelper.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"java:S2699","java:S2187"})
public abstract class _BasicImmutableTest<T> {

  protected final Logger log = LoggerFactory.getLogger(_BasicImmutableTest.class);
  protected final JsonMapper<T> mapper;

  protected _BasicImmutableTest(Class<T> type) {
    this.mapper = jsonMapper(type);
  }

  @Test
  public void factory_method() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Test
  public void create_minimal_dto() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Test
  public void convert_json() {
    final T dto = createDto();
    final var json = mapper.toJson(dto);

    assertThat(mapper.fromJson(json)).isEqualTo(dto);
  }

  protected abstract T createDto();
}
