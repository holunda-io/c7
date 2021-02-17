package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.GROUP_ID;
import static io.holunda.commons.immutables._Fixtures.IDENTITY_LINK;
import static io.holunda.commons.immutables._Fixtures.PROCESS_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.TASK_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.USER_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

import org.camunda.bpm.engine.task.IdentityLinkType;

@SuppressWarnings({"java:S2699"})
public class IdentityLinkTest extends _BasicImmutableTest<ImmutableIdentityLink> {

  public IdentityLinkTest() {
    super(ImmutableIdentityLink.class);
  }

  @Override
  public void factory_method() {
    final ImmutableIdentityLink dto = createDto();

    Assertions.assertThat(dto).hasId(IDENTITY_LINK.getId());
    Assertions.assertThat(dto).hasType(IdentityLinkType.ASSIGNEE);
    Assertions.assertThat(dto).hasUserId(USER_ID);
    Assertions.assertThat(dto).hasGroupId(GROUP_ID);
    Assertions.assertThat(dto).hasTaskId(TASK_ID);
    Assertions.assertThat(dto).hasProcessDefId(PROCESS_DEFINITION_ID);
    Assertions.assertThat(dto).hasTenantId(TENANT_ID);
  }


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
    return CamundaImmutables.identityLink(IDENTITY_LINK);
  }
}
