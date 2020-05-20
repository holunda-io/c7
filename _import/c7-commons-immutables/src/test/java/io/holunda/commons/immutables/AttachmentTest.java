package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.ATTACHMENT;
import static io.holunda.commons.immutables._Fixtures.DATE_NOW;
import static io.holunda.commons.immutables._Fixtures.DATE_TOMORROW;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TASK_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

public class AttachmentTest extends _BasicImmutableTest<ImmutableAttachment>{

  public AttachmentTest() {
    super(ImmutableAttachment.class);
  }

  @Override
  public void factory_method() {
    ImmutableAttachment dto = createDto();

    assertThat(dto).hasId(ATTACHMENT.getId());
    assertThat(dto).hasName(ATTACHMENT.getName());
    assertThat(dto).hasDescription(ATTACHMENT.getDescription());
    assertThat(dto).hasType(ATTACHMENT.getType());
    assertThat(dto).hasUrl(ATTACHMENT.getUrl());
    assertThat(dto).hasTaskId(TASK_ID);
    assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    assertThat(dto).hasCreateTime(DATE_NOW);
    assertThat(dto).hasRemovalTime(DATE_TOMORROW);
    assertThat(dto).hasRootProcessInstanceId(ATTACHMENT.getProcessInstanceId());
  }

  @Override
  public void create_minimal_dto() {
    ImmutableAttachment dto = ImmutableAttachment.builder()
      .id(uuid())
      .processInstanceId(PROCESS_INSTANCE_ID)
      .build();
  }

  @Override
  ImmutableAttachment createDto() {
    return CamundaImmutables.attachment(_Fixtures.ATTACHMENT);
  }
}
