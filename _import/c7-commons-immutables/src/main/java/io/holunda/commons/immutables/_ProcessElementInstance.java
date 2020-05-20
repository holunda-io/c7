package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.ProcessElementInstance;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableProcessElementInstance.class)
@JsonSerialize(as = ImmutableProcessElementInstance.class)
public interface _ProcessElementInstance extends ProcessElementInstance {

  @Override
  @Nullable
  String getParentActivityInstanceId();

}
