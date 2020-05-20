package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CurrentTimestamp;
import java.util.Date;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableEventSubscription.class)
@JsonSerialize(as = ImmutableEventSubscription.class)
public interface _EventSubscription extends EventSubscription, CurrentTimestamp {

  @Nullable
  @Override
  String getTenantId();

  @Override
  default Date getCreated() {
    return getNow().get();
  }
}
