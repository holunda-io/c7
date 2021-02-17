package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.ACTIVITY_ID;
import static io.holunda.commons.immutables._Fixtures.CASE_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.CASE_EXECUTION;
import static io.holunda.commons.immutables._Fixtures.CASE_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

import org.camunda.bpm.engine.ActivityTypes;

@SuppressWarnings({"java:S2699"})
public class CaseExecutionTest extends _BasicImmutableTest<ImmutableCaseExecution> {

  public CaseExecutionTest() {
    super(ImmutableCaseExecution.class);
  }

  @Override
  public void factory_method() {
    ImmutableCaseExecution dto = createDto();

    assertThat(dto).hasId(CASE_EXECUTION.getId());
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
  ImmutableCaseExecution createDto() {
    return CamundaImmutables.caseExecution(_Fixtures.CASE_EXECUTION);
  }
}
