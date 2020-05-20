package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.uuid;

import org.camunda.bpm.engine.task.IdentityLinkType;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class IdentityLinkTest extends _BasicImmutableTest<ImmutableIdentityLink> {

  public IdentityLinkTest() {
    super(ImmutableIdentityLink.class);
  }

  @Override
  public void factory_method() {
    super.factory_method();
  }

  @Test
  @Override
  public void create_minimal_dto() {
    ImmutableIdentityLink dto = ImmutableIdentityLink.builder()
      .id(uuid())
      .type(IdentityLinkType.ASSIGNEE)
      .taskId(uuid())
      .build();
  }

  @Override
  ImmutableIdentityLink createDto() {
    return CamundaImmutables.identityLink(null);
  }
}
