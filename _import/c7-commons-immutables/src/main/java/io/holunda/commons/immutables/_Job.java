package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import java.util.Date;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.runtime.Job;
import org.immutables.value.Value;
import org.immutables.value.Value.Default;
import org.jetbrains.annotations.Nullable;

@Value.Immutable(prehash = true)
@JsonDeserialize(as = ImmutableJob.class)
@JsonSerialize(as = ImmutableJob.class)
@CamundaPojoStyle
public interface _Job extends Job {


  @Default
  @Override
  default int getRetries() {
    return JobEntity.DEFAULT_RETRIES;
  }

  @Default
  @Override
  default Date getCreateTime() {
    return new Date();
  }

  @Default
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
}
