package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import java.util.stream.Stream;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.immutables.value.Value;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableActivityInstance.class)
@JsonSerialize(as = ImmutableActivityInstance.class)
public interface _ActivityInstance extends ActivityInstance, _ProcessElementInstance {

  @Override
  default ActivityInstance[] getActivityInstances(String activityId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  default TransitionInstance[] getTransitionInstances(String activityId) {
    throw new UnsupportedOperationException("not implemented");
  }
}
