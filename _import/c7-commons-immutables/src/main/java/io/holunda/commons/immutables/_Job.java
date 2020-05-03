package io.holunda.commons.immutables;

import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import java.util.Date;
import org.camunda.bpm.engine.runtime.Job;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
public interface _Job extends Job {

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
