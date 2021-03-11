package io.holunda.commons.immutables.externaltask;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.task.Task;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#externalTask(ExternalTask)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableExternalTask.class)
@JsonSerialize(as = ImmutableExternalTask.class)
@SuppressWarnings("java:S114")
interface _ExternalTask extends ExternalTask {
  @Override
  String getId();

  @Override
  @Nullable
  String getBusinessKey();

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
  @Nullable
  String getTenantId();

  @Override
  default boolean isSuspended() {
    return false;
  }

  @Override
  default long getPriority() {
    return Task.PRIORITY_NORMAL;
  }

  @Override
  default Map<String, String> getExtensionProperties() {
    return Collections.emptyMap();
  }

}
