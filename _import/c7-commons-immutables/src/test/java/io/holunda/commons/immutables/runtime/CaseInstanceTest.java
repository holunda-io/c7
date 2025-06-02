package io.holunda.commons.immutables.runtime;

import io.holunda.commons.immutables.CamundaImmutables;
import io.holunda.commons.immutables._BasicImmutableTest;
import io.holunda.commons.immutables._Fixtures;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.ACTIVITY_ID;
import static io.holunda.commons.immutables._Fixtures.CASE_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.CASE_EXECUTION;
import static io.holunda.commons.immutables._Fixtures.CASE_INSTANCE;
import static io.holunda.commons.immutables._Fixtures.CASE_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

@SuppressWarnings({"java:S2699","java:S2187"})
public class CaseInstanceTest extends _BasicImmutableTest<ImmutableCaseInstance> {

  public CaseInstanceTest() {
    super(ImmutableCaseInstance.class);
  }

  @Override
  public void factory_method() {
    ImmutableCaseInstance dto = createDto();

    assertThat(dto).hasId(CASE_INSTANCE.getId());
    assertThat(dto).hasCaseInstanceId(CASE_INSTANCE_ID);
    assertThat(dto).hasCaseDefinitionId(CASE_DEFINITION_ID);
    assertThat(dto).hasActivityId(ACTIVITY_ID);
    assertThat(dto).hasActivityName(CASE_EXECUTION.getActivityName());
    assertThat(dto).hasActivityType(CASE_EXECUTION.getActivityType());
    assertThat(dto).hasActivityDescription(CASE_EXECUTION.getActivityDescription());
    assertThat(dto).hasParentId(null);
    assertThat(dto).isRequired();
    assertThat(dto).isAvailable();
    assertThat(dto).isActive();
    assertThat(dto).isEnabled();
    assertThat(dto).isTerminated();
    assertThat(dto).hasTenantId(TENANT_ID);
  }

  @Override
  public void create_minimal_dto() {
    ImmutableCaseExecution.builder()
      .id(uuid())
      .build();
  }

  @Override
  protected ImmutableCaseInstance createDto() {
    return CamundaImmutables.caseInstance(_Fixtures.CASE_INSTANCE);
  }
}
