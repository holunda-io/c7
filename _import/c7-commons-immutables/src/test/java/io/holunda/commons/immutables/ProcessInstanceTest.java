package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.BUSINESS_KEY;
import static io.holunda.commons.immutables._Fixtures.CASE_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

@SuppressWarnings({"java:S2699"})
public class ProcessInstanceTest extends  _BasicImmutableTest<ImmutableProcessInstance> {

  public ProcessInstanceTest() {
    super(ImmutableProcessInstance.class);
  }

  @Override
  public void factory_method() {
    final ImmutableProcessInstance dto = createDto();

    assertThat(dto).hasId(PROCESS_INSTANCE.getId());
    assertThat(dto).hasProcessDefinitionId(PROCESS_DEFINITION_ID);
    assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    assertThat(dto).hasBusinessKey(BUSINESS_KEY);
    assertThat(dto).hasTenantId(TENANT_ID);
    assertThat(dto).hasRootProcessInstanceId(null);
    assertThat(dto).hasCaseInstanceId(CASE_INSTANCE_ID);
    assertThat(dto).isSuspended();
    assertThat(dto).isEnded();
    org.assertj.core.api.Assertions.assertThat(dto.getVariables().get("foo")).isEqualTo("bar");
  }

  @Override
  public void create_minimal_dto() {

    ImmutableProcessInstance dto = ImmutableProcessInstance.builder()
      .id(uuid())
      .processDefinitionId(uuid())
      .build();
  }


  @Override
  ImmutableProcessInstance createDto() {
    return CamundaImmutables.processInstance(PROCESS_INSTANCE);
  }
}
