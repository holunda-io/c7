package io.holunda.commons.immutables.runtime;

import io.holunda.commons.immutables.CamundaImmutables;
import io.holunda.commons.immutables._BasicImmutableTest;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.PROCESS_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_ELEMENT_INSTANCE;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

@SuppressWarnings({"java:S2699","java:S2187"})
public class ProcessElementInstanceTest extends _BasicImmutableTest<ImmutableProcessElementInstance> {

  public ProcessElementInstanceTest() {
    super(ImmutableProcessElementInstance.class);
  }

  @Override
  public void factory_method() {
    final ImmutableProcessElementInstance dto = createDto();

    assertThat(dto).hasId(PROCESS_ELEMENT_INSTANCE.getId());
    assertThat(dto).hasParentActivityInstanceId(PROCESS_ELEMENT_INSTANCE.getParentActivityInstanceId());
    assertThat(dto).hasProcessDefinitionId(PROCESS_DEFINITION_ID);
    assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
  }

  @Override
  public void create_minimal_dto() {
    ImmutableProcessElementInstance.builder()
      .id(uuid())
      .processDefinitionId(PROCESS_DEFINITION_ID)
      .processInstanceId(PROCESS_INSTANCE_ID)
      .build();
  }

  @Override
  protected ImmutableProcessElementInstance createDto() {
    return CamundaImmutables.processElementInstance(PROCESS_ELEMENT_INSTANCE);
  }
}
