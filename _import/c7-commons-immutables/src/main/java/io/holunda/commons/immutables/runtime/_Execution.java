package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.runtime.Execution;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#execution(Execution)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableExecution.class)
@JsonSerialize(as = ImmutableExecution.class)
@SuppressWarnings("java:S114")
interface _Execution extends Execution {

  @Override
  String getId();

  @Override
  String getProcessInstanceId();

  @Nullable
  @Override
  String getTenantId();

  @Override
  default boolean isSuspended() {
    return false;
  }

  @Override
  default boolean isEnded() {
    return false;
  }
}
