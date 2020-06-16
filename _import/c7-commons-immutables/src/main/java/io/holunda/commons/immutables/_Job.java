package io.holunda.commons.immutables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CurrentTimestamp;
import java.util.Date;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.runtime.Job;
import org.immutables.value.Value;
import org.immutables.value.Value.Default;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@JsonDeserialize(as = ImmutableJob.class)
@JsonSerialize(as = ImmutableJob.class)
@CamundaPojoStyle
interface _Job extends Job, CurrentTimestamp {


  @Override
  default int getRetries() {
    return JobEntity.DEFAULT_RETRIES;
  }

  @Override
  default Date getCreateTime() {
    return getNow().get();
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
}
