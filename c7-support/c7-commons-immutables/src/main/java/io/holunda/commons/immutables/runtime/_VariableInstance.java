package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#variableInstance(VariableInstance)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableVariableInstance.class)
@JsonSerialize(as = ImmutableVariableInstance.class)
@SuppressWarnings("java:S114")
interface _VariableInstance extends VariableInstance {

  @Override
  String getId();

  @Override
  String getName();

  @Override
  String getTypeName();

  @Override
  @Nullable
  Object getValue();

  @Override
  TypedValue getTypedValue();

  @Override
  @Nullable
  String getProcessInstanceId();

  @Override
  @Nullable
  String getExecutionId();

  @Override
  @Nullable
  String getProcessDefinitionId();

  @Override
  @Nullable
  String getCaseInstanceId();

  @Override
  @Nullable
  String getCaseExecutionId();

  @Override
  @Nullable
  String getTaskId();

  @Override
  @Nullable
  String getBatchId();

  @Override
  @Nullable
  String getActivityInstanceId();

  @Override
  @Nullable
  String getErrorMessage();

  @Override
  @Nullable
  String getTenantId();
}
