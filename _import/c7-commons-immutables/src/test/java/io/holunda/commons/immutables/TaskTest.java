package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutablesTest.uuid;
import static io.holunda.commons.immutables.JacksonHelper.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

import io.holunda.commons.immutables.CamundaImmutablesTest.BasicImmutableTest;
import io.holunda.commons.immutables.JacksonHelper.JsonMapper;
import org.junit.Test;

public class TaskTest implements BasicImmutableTest {

  private final JsonMapper<ImmutableTask> mapper = jsonMapper(ImmutableTask.class);

  @Test
  @Override
  public void create_minimal_dto() {
    ImmutableTask dto = ImmutableTask.builder()
      .id(uuid())
      .executionId(uuid())
      .taskDefinitionKey("task")
      .build();
  }

  @Test
  @Override
  public void convert_json() {
    ImmutableTask dto = ImmutableTask.builder()
      .id(uuid())
      .name("The Task")
      .description("the description")
      .executionId(uuid())
      .taskDefinitionKey("task")
      .build();

    String json = mapper.toJson(dto);
    assertThat(mapper.fromJson(json)).isEqualTo(dto);
  }
}
