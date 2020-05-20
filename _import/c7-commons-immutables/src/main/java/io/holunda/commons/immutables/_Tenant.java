package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Tenant;
import org.immutables.value.Value;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableTenant.class)
@JsonSerialize(as = ImmutableTenant.class)
interface _Tenant extends _TenantWithoutSetters {

}

interface _TenantWithoutSetters extends Tenant {

  @Override
  default void setId(String id) {
    throw UNMODIFIABLE;
  }

  @Override
  default void setName(String name) {
    throw UNMODIFIABLE;
  }
}
