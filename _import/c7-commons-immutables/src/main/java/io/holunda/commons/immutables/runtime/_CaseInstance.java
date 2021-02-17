package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableCaseInstance.class)
@JsonSerialize(as = ImmutableCaseInstance.class)
@SuppressWarnings("java:S114")
public interface _CaseInstance extends _CaseExecution, CaseInstance {

  @Override
  @Nullable
  String getBusinessKey();

  @Override
  default boolean isCompleted() {
    return false;
  }
}
