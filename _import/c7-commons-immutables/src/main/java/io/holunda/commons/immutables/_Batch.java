package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.batch.Batch;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableBatch.class)
@JsonSerialize(as = ImmutableBatch.class)
public interface _Batch extends Batch {

  @Override
  @Nullable
  String getCreateUserId();

  @Nullable
  @Override
  String getTenantId();

  @Value.Default
  @Override
  default boolean isSuspended() {
    return false;
  }
}
