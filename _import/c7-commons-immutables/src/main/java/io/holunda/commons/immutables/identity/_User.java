package io.holunda.commons.immutables.identity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.User;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableUser.class)
@JsonSerialize(as = ImmutableUser.class)
@SuppressWarnings({"java:S114"})
interface _User extends _UserWithoutSetters {

  @Override
  String getId();

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

/**
 * Throwing {@link UnsupportedOperationException} for all setters to keep
 * the {@link _User} clean.
 */
@SuppressWarnings({"java:S114", "java:S1133"})
interface _UserWithoutSetters extends User {

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
  default void setFirstName(String firstName) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setLastName(String lastName) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setEmail(String email) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setPassword(String password) {
    throw UNMODIFIABLE;
  }
}
