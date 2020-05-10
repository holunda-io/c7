package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.Facets.WithTenantId;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.Execution;
import org.immutables.value.Value;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableExecution.class)
@JsonSerialize(as = ImmutableExecution.class)
public interface _Execution extends Execution, WithTenantId {
  // empty
}
