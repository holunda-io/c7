package io.holunda.commons.immutables.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.task.IdentityLink;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#identityLink(IdentityLink)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableIdentityLink.class)
@JsonSerialize(as = ImmutableIdentityLink.class)
@SuppressWarnings("java:S114")
interface _IdentityLink extends IdentityLink {

  /**
   * If the identity link involves a user, then this will be a non-null id of a user. That userId can be used to query for user information
   * through the {@link UserQuery} API.
   */
  @Nullable
  @Override
  String getUserId();

  /**
   * If the identity link involves a group, then this will be a non-null id of a group. That groupId can be used to query for user
   * information through the {@link GroupQuery} API.
   */
  @Nullable
  @Override
  String getGroupId();

  @Nullable
  @Override
  String getProcessDefId();

  @Nullable
  @Override
  String getTenantId();

  @Override
  String getId();

  /**
   * Has to match one of the constants provided by {@link org.camunda.bpm.engine.task.IdentityLinkType}.
   *
   * @return type
   */
  @Override
  String getType();

  @Override
  String getTaskId();

  @Value.Check
  default void validate() {
    if (!IdentityLinkTypeEnum.TYPES.contains(getType())) {
      throw new IllegalArgumentException(String.format("type has to be on of %s, was '%s'", IdentityLinkTypeEnum.TYPES, getType()));
    }
  }
}
