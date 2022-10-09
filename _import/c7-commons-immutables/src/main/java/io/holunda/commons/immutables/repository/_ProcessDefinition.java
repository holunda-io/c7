package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#processDefinition(ProcessDefinition)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableProcessDefinition.class)
@JsonSerialize(as = ImmutableProcessDefinition.class)
@SuppressWarnings("java:S114")
public interface _ProcessDefinition extends _ResourceDefinition, ProcessDefinition {

  @Override
  @Nullable
  String getDescription();

  @Override
  default boolean hasStartFormKey() {
    return false;
  }

  @Override
  default boolean isSuspended() {
    return false;
  }

  @Override
  default boolean isStartableInTasklist() {
    return false;
  }

  @Override
  @Nullable
  String getVersionTag();
}
