package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableActivityInstance.class)
@JsonSerialize(as = ImmutableActivityInstance.class)
interface _ActivityInstance extends ActivityInstance {

  @Override
  @Nullable
  String getActivityName();

  @Override
  @Nullable
  String getParentActivityInstanceId();

  @Override
  default ActivityInstance[] getChildActivityInstances() {
    return new ActivityInstance[0];
  }

  @Override
  default TransitionInstance[] getChildTransitionInstances() {
    return new TransitionInstance[0];
  }

  @Override
  default String[] getExecutionIds() {
    return new String[0];
  }

  @Override
  default String[] getIncidentIds() {
    return new String[0];
  }

  @Override
  default Incident[] getIncidents() {
    return new Incident[0];
  }


  @Override
  default ActivityInstance[] getActivityInstances(String activityId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  default TransitionInstance[] getTransitionInstances(String activityId) {
    throw new UnsupportedOperationException("not implemented");
  }
}
