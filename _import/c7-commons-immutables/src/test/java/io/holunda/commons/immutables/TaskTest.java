package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.uuid;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TaskTest extends _BasicImmutableTest<ImmutableTask> {

  public TaskTest() {
    super(ImmutableTask.class);
  }

  @Test
  @Override
  public void create_minimal_dto() {
    ImmutableTask dto = ImmutableTask.builder()
      .id(uuid())
      .executionId(uuid())
      .taskDefinitionKey("task")
      .build();
  }

  @Override
  ImmutableTask createDto() {
    return CamundaImmutables.task(null);
  }
}
