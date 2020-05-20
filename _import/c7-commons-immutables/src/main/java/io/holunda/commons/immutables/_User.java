package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.User;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableUser.class)
@JsonSerialize(as = ImmutableUser.class)
interface _User extends _UserWithoutSetters {

  @Override
  @Nullable
  String getFirstName();

  @Override
  @Nullable
  String getLastName();

  @Override
  @Nullable
  String getEmail();

  @Override
  @Nullable
  String getPassword();
}

interface _UserWithoutSetters extends User {

  @Override
  @Deprecated
  default void setId(String id) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setFirstName(String firstName) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setLastName(String lastName) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setEmail(String email) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setPassword(String password) {
    throw UNMODIFIABLE;
  }
}
