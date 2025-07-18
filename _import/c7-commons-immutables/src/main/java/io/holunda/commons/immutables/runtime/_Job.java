package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.runtime.Job;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#job(Job)
 */
@Value.Immutable
@JsonDeserialize(as = ImmutableJob.class)
@JsonSerialize(as = ImmutableJob.class)
@CamundaPojoStyle
@SuppressWarnings("java:S114")
interface _Job extends Job {

  @Override
  String getId();

  @Override
  String getProcessInstanceId();

  @Override
  String getProcessDefinitionId();

  @Override
  String getProcessDefinitionKey();

  @Override
  String getExecutionId();

  @Override
  String getDeploymentId();

  @Override
  String getJobDefinitionId();

  @Override
  long getPriority();

  @Override
  default int getRetries() {
    return JobEntity.DEFAULT_RETRIES;
  }

  @Override
  default Date getCreateTime() {
    return ClockUtil.getCurrentTime();
  }

  @Override
  default boolean isSuspended() {
    return false;
  }

  @Override
  @Nullable
  Date getDuedate();

  @Override
  @Nullable
  String getExceptionMessage();

  @Override
  @Nullable
  String getTenantId();

  @Override
  @Nullable
  String getFailedActivityId();

  @Override
  String getRootProcessInstanceId();

  @Override
  String getBatchId();
}
