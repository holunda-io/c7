package io.holunda.commons.immutables.task;

import io.holunda.commons.immutables.CamundaImmutables;
import io.holunda.commons.immutables._BasicImmutableTest;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.COMMENT;
import static io.holunda.commons.immutables._Fixtures.DATE_NOW;
import static io.holunda.commons.immutables._Fixtures.DATE_TOMORROW;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TASK_ID;
import static io.holunda.commons.immutables._Fixtures.USER_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

@SuppressWarnings({"java:S2699","java:S2187"})
public class CommentTest extends _BasicImmutableTest<ImmutableComment> {

  public CommentTest() {
    super(ImmutableComment.class);
  }

  @Override
  public void factory_method() {
    final ImmutableComment dto = createDto();

    assertThat(dto).hasId(COMMENT.getId());
    assertThat(dto).hasUserId(USER_ID);
    assertThat(dto).hasTime(DATE_NOW);
    assertThat(dto).hasRemovalTime(DATE_TOMORROW);
    assertThat(dto).hasTaskId(TASK_ID);
    assertThat(dto).hasRootProcessInstanceId(null);
    assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    assertThat(dto).hasFullMessage(COMMENT.getFullMessage());
  }

  @Override
  public void create_minimal_dto() {
    ImmutableComment.builder()
      .id(uuid())
      .taskId(uuid())
      .processInstanceId(uuid())
      .build();
  }

  @Override
  protected ImmutableComment createDto() {
    return CamundaImmutables.comment(COMMENT);
  }
}
