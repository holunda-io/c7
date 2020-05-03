package io.holunda.commons.immutables;

import io.holunda.commons.immutables.CamundaImmutables.Facets.WithTenantId;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.Execution;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
public interface _Execution extends Execution, WithTenantId {
  // empty
}
