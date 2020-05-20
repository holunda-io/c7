package io.holunda.commons.immutables;


import static io.holunda.commons.immutables._Fixtures.ACTIVITY_ID;
import static io.holunda.commons.immutables._Fixtures.ACTIVITY_INSTANCE;
import static io.holunda.commons.immutables._Fixtures.ACTIVITY_NAME;
import static io.holunda.commons.immutables._Fixtures.PROCESS_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

import org.camunda.bpm.engine.ActivityTypes;

public class ActivityInstanceTest extends _BasicImmutableTest<ImmutableActivityInstance> {

  public ActivityInstanceTest() {
    super(ImmutableActivityInstance.class);
  }

  @Override
  public void factory_method() {
    ImmutableActivityInstance dto = createDto();

    Assertions.assertThat(dto).hasId(ACTIVITY_INSTANCE.getId());
    Assertions.assertThat(dto).hasActivityId(ACTIVITY_ID);
    Assertions.assertThat(dto).hasActivityName(ACTIVITY_NAME);
    Assertions.assertThat(dto).hasParentActivityInstanceId(null);
    Assertions.assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    Assertions.assertThat(dto).hasProcessDefinitionId(PROCESS_DEFINITION_ID);
    Assertions.assertThat(dto).hasActivityType(ActivityTypes.TASK_USER_TASK);
    org.assertj.core.api.Assertions.assertThat(dto.getChildActivityInstances()).isEmpty();
    org.assertj.core.api.Assertions.assertThat(dto.getChildTransitionInstances()).isEmpty();
    org.assertj.core.api.Assertions.assertThat(dto.getExecutionIds()).isEmpty();
    org.assertj.core.api.Assertions.assertThat(dto.getIncidentIds()).isEmpty();

  }

  @Override
  public void create_minimal_dto() {
    ImmutableActivityInstance dto = ImmutableActivityInstance.builder()
      .id(uuid())
      .processDefinitionId(PROCESS_DEFINITION_ID)
      .processInstanceId(PROCESS_INSTANCE_ID)
      .activityId(ACTIVITY_ID)
      .activityType(ActivityTypes.TASK_USER_TASK)
      .build();
  }

  @Override
  ImmutableActivityInstance createDto() {
    return CamundaImmutables.activityInstance(_Fixtures.ACTIVITY_INSTANCE);
  }
}
