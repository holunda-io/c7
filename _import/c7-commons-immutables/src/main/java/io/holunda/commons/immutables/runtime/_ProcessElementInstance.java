package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.runtime.ProcessElementInstance;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#processElementInstance(ProcessElementInstance)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableProcessElementInstance.class)
@JsonSerialize(as = ImmutableProcessElementInstance.class)
@SuppressWarnings("java:S114")
interface _ProcessElementInstance extends ProcessElementInstance {

  @Override
  String getId();

  @Override
  String getProcessDefinitionId();

  @Override
  String getProcessInstanceId();

  @Override
  @Nullable
  String getParentActivityInstanceId();

}
