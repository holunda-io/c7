package io.holunda.commons.immutables.identity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Tenant;
import org.immutables.value.Value;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableTenant.class)
@JsonSerialize(as = ImmutableTenant.class)
@SuppressWarnings({"java:S114"})
interface _Tenant extends _TenantWithoutSetters {
  @Override
  String getId();

  @Override
  String getName();
}

/**
 * Throwing {@link UnsupportedOperationException} for all setters to keep
 * the {@link _Tenant} clean.
 */
@SuppressWarnings({"java:S114", "java:S1133"})
interface _TenantWithoutSetters extends Tenant {

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setId(String id) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setName(String name) {
    throw UNMODIFIABLE;
  }
}
