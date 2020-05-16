package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.task.IdentityLink;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableIdentityLink.class)
@JsonSerialize(as = ImmutableIdentityLink.class)
public interface _IdentityLink extends IdentityLink {

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
}
