package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.uuid;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.Ignore;

@Ignore
public class ProcessInstanceTest extends  _BasicImmutableTest<ImmutableProcessInstance> {

  private static final String ID = UUID.randomUUID().toString();

  public ProcessInstanceTest() {
    super(ImmutableProcessInstance.class);
  }


  @Override
  public void create_minimal_dto() {

    ImmutableProcessInstance dto = ImmutableProcessInstance.builder()
      .id(ID)
      .processDefinitionId(uuid())
      .build();

    assertThat(dto.getProcessInstanceId()).isEqualTo(ID);

  }


  @Override
  ImmutableProcessInstance createDto() {
    return CamundaImmutables.processInstance(null);
  }
}
