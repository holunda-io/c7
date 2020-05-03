package io.holunda.commons.immutables;

import io.holunda.commons.immutables.CamundaImmutables.Facets.WithTenantId;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.immutables.value.Value;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
public interface _EventSubscription extends EventSubscription, WithTenantId {
  // empty
}
