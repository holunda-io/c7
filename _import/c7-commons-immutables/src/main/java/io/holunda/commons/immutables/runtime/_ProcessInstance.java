package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#processInstance(ProcessInstance)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableProcessInstance.class)
@JsonSerialize(as = ImmutableProcessInstance.class)
@SuppressWarnings("java:S114")
interface _ProcessInstance extends _Execution, ProcessInstanceWithVariables, ProcessInstance, Execution {

  @Override
  String getId();

  @Override
  default String getProcessInstanceId() {
    return getId();
  }

  @Nullable
  @Override
  String getRootProcessInstanceId();

  @Override
  @JsonSerialize(as = VariableMapImpl.class)
  @JsonDeserialize(as = VariableMapImpl.class)
  default VariableMap getVariables() {
    return Variables.createVariables();
  }

  @Override
  @Nullable
  String getCaseInstanceId();

  @Nullable
  @Override
  String getBusinessKey();

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
