package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.Facets.WithTenantId;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.immutables.value.Value;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableEventSubscription.class)
@JsonSerialize(as = ImmutableEventSubscription.class)
public interface _EventSubscription extends EventSubscription, WithTenantId {
  // empty
}
