package io.holunda.commons.immutables.management;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.management.JobDefinition;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#jobDefinition(JobDefinition)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableJobDefinition.class)
@JsonSerialize(as = ImmutableJobDefinition.class)
@SuppressWarnings("java:S114")
public interface _JobDefinition extends JobDefinition {

  @Override
  String getId();

  @Override
  @Nullable
  String getProcessDefinitionId();

  @Override
  @Nullable
  String getProcessDefinitionKey();

  @Override
  String getJobType();

  @Override
  String getJobConfiguration();

  @Override
  @Nullable
  String getActivityId();

  @Override
  default boolean isSuspended() {
    return false;
  }

  @Override
  @Nullable
  Long getOverridingJobPriority();

  @Override
  @Nullable
  String getTenantId();

  @Override
  @Nullable
  String getDeploymentId();
}
