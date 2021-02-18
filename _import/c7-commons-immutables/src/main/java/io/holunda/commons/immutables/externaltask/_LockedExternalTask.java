package io.holunda.commons.immutables.externaltask;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#lockedExternalTask(LockedExternalTask)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableLockedExternalTask.class)
@JsonSerialize(as = ImmutableLockedExternalTask.class)
@SuppressWarnings("java:S114")
interface _LockedExternalTask extends LockedExternalTask {
  @Override
  String getId();

  @Override
  String getTopicName();

  @Override
  String getWorkerId();

  @Override
  Date getLockExpirationTime();

  @Override
  @Nullable
  String getProcessInstanceId();

  @Override
  @Nullable
  String getExecutionId();

  @Override
  @Nullable
  String getActivityId();

  @Override
  @Nullable
  String getActivityInstanceId();

  @Override
  @Nullable
  String getProcessDefinitionId();

  @Override
  @Nullable
  String getProcessDefinitionKey();

  @Override
  @Nullable
  String getProcessDefinitionVersionTag();

  @Override
  Integer getRetries();

  @Override
  @Nullable
  String getErrorMessage();

  @Override
  default long getPriority() {
    return Task.PRIORITY_NORMAL;
  }

  @Override
  default Map<String, String> getExtensionProperties() {
    return Collections.emptyMap();
  }

  @Override
  @Nullable
  String getBusinessKey();

  @Override
  default String getErrorDetails() {
    return null;
  }

  @Override
  String getTenantId();

  @Override
  default VariableMap getVariables() {
    return Variables.createVariables();
  }
}
