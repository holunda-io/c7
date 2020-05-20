package io.holunda.commons.immutables;


import org.camunda.bpm.engine.ActivityTypes;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.junit.Ignore;

@Ignore
public class ActivityInstanceTest extends _BasicImmutableTest<ImmutableActivityInstance> {

  private static final ActivityInstance ACTIVITY_INSTANCE = new ActivityInstance() {
    @Override
    public String getActivityId() {
      return _Fixtures.ACTIVITY_ID;
    }

    @Override
    public String getActivityName() {
      return _Fixtures.ACTIVITY_NAME;
    }

    @Override
    public String getActivityType() {
      return ActivityTypes.TASK_USER_TASK;
    }

    @Override
    public ActivityInstance[] getChildActivityInstances() {
      return new ActivityInstance[0];
    }

    @Override
    public TransitionInstance[] getChildTransitionInstances() {
      return new TransitionInstance[0];
    }

    @Override
    public String[] getExecutionIds() {
      return new String[0];
    }

    @Override
    public ActivityInstance[] getActivityInstances(String activityId) {
      return new ActivityInstance[0];
    }

    @Override
    public TransitionInstance[] getTransitionInstances(
      String activityId) {
      return new TransitionInstance[0];
    }

    @Override
    public String[] getIncidentIds() {
      return new String[0];
    }

    @Override
    public String getId() {
      return _Fixtures.uuid();
    }

    @Override
    public String getParentActivityInstanceId() {
      return null;
    }

    @Override
    public String getProcessDefinitionId() {
      return _Fixtures.PROCESS_DEFINITION_ID;
    }

    @Override
    public String getProcessInstanceId() {
      return _Fixtures.uuid();
    }
  };

  public ActivityInstanceTest() {
    super(ImmutableActivityInstance.class);
  }

  @Override
  public void create_minimal_dto() {
    ImmutableActivityInstance dto = CamundaImmutables.activityInstance(ACTIVITY_INSTANCE);


  }

  @Override
  ImmutableActivityInstance createDto() {
    return CamundaImmutables.activityInstance(null);
  }
}
