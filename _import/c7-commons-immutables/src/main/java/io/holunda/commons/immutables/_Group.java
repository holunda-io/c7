package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.immutables.value.Value;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableGroup.class)
@JsonSerialize(as = ImmutableGroup.class)
@SuppressWarnings("java:S114")
interface _Group extends _GroupWithoutSetters {
  @Override
  String getId();

  @Override
  String getName();

  @Override
  String getType();
}

@SuppressWarnings({"java:S114", "java:S1133"})
interface _GroupWithoutSetters extends Group {

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

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setType(String string) {
    throw UNMODIFIABLE;
  }
}
