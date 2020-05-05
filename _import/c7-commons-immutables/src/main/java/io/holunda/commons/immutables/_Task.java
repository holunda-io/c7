package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.Facets.UNMODIFIABLE;

import io.holunda.commons.immutables.CamundaImmutables.Facets.WithTenantId;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import java.util.Date;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
public interface _Task extends Task, WithTenantId {

  @Override
  @Nullable
  String getName();

  @Override
  default void setName(String name) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getDescription();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param description the description, unused
   */
  @Override
  @Deprecated
  default void setDescription(String description) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param priority the priority, unused
   */
  @Override
  default void setPriority(int priority) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getOwner();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param owner the owner, unused
   */
  @Override
  default void setOwner(String owner) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getAssignee();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param assignee the assignee, unused
   */
  @Override
  default void setAssignee(String assignee) {
    throw UNMODIFIABLE;
  }

  @Override
  DelegationState getDelegationState();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param delegationState the delegationState, unused
   */
  @Override
  default void setDelegationState(DelegationState delegationState) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getProcessInstanceId();

  @Override
  String getExecutionId();

  @Override
  @Nullable
  String getProcessDefinitionId();

  @Override
  @Nullable
  String getCaseInstanceId();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param caseInstanceId the caseInstanceId, unused
   */
  @Override
  default void setCaseInstanceId(String caseInstanceId) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getCaseExecutionId();

  @Override
  @Nullable
  String getCaseDefinitionId();

  @Override
  Date getCreateTime();

  @Override
  String getTaskDefinitionKey();

  @Override
  @Nullable
  Date getDueDate();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param dueDate the dueDate, unused
   */
  @Override
  default void setDueDate(Date dueDate) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  Date getFollowUpDate();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param followUpDate the followUpDate, unused
   */
  @Override
  default void setFollowUpDate(Date followUpDate) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param userId the userId, unused
   */
  @Override
  default void delegate(String userId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param parentTaskId the parentTaskId, unused
   */
  @Override
  default void setParentTaskId(String parentTaskId) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getParentTaskId();

  @Override
  boolean isSuspended();

  @Override
  @Nullable
  String getFormKey();

  /**
   * @deprecated throws {@link UnsupportedOperationException}
   * @throws UnsupportedOperationException
   * @param tenantId the tenantId, unused
   */
  @Override
  default void setTenantId(String tenantId) {
    throw UNMODIFIABLE;
  }
}
