package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.immutables.value.Value;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableGroup.class)
@JsonSerialize(as = ImmutableGroup.class)
interface _Group extends _GroupWithoutSetters {

}

interface _GroupWithoutSetters extends Group {

  @Override
  @Deprecated
  default void setId(String id) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setName(String name) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setType(String string) {
    throw UNMODIFIABLE;
  }
}
