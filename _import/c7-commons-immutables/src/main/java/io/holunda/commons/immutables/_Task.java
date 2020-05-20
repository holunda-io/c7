package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.NOW;
import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import java.util.Date;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;
import org.immutables.value.Value;
import org.immutables.value.Value.Default;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableTask.class)
@JsonSerialize(as = ImmutableTask.class)
public interface _Task extends Task, _TaskWithoutSetters {

  @Nullable
  @Override
  String getName();

  @Nullable
  @Override
  String getDescription();

  @Nullable
  @Override
  String getOwner();

  @Nullable
  @Override
  String getAssignee();

  @Nullable
  @Override
  DelegationState getDelegationState();

  @Nullable
  @Override
  String getProcessInstanceId();

  @Override
  String getExecutionId();

  @Nullable
  @Override
  String getProcessDefinitionId();

  @Nullable
  @Override
  String getCaseInstanceId();

  @Nullable
  @Override
  String getCaseExecutionId();

  @Nullable
  @Override
  String getCaseDefinitionId();

  @Override
  default Date getCreateTime() {
    return NOW.get();
  }

  @Override
  String getTaskDefinitionKey();

  @Nullable
  @Override
  Date getDueDate();

  @Nullable
  @Override
  Date getFollowUpDate();

  /**
   * @param userId the userId, unused
   * @deprecated throws {@link UnsupportedOperationException}
   */
  @Override
  default void delegate(String userId) {
    throw UNMODIFIABLE;
  }


  @Default
  @Override
  default int getPriority() {
    return PRIORITY_NORMAL;
  }

  @Nullable
  @Override
  String getParentTaskId();

  @Default
  @Override
  default boolean isSuspended() {
    return false;
  }

  @Nullable
  @Override
  String getFormKey();

  @Nullable
  @Override
  String getTenantId();

}

/**
 * Throwing {@link UnsupportedOperationException} for all setters to keep
 * the {@link _Task} clean.
 */
interface _TaskWithoutSetters extends Task {

  @Override
  @Deprecated
  default void setName(String name) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setDescription(String description) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setPriority(int priority) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setOwner(String owner) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setAssignee(String assignee) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setDelegationState(DelegationState delegationState) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setCaseInstanceId(String caseInstanceId) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setDueDate(Date dueDate) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setFollowUpDate(Date followUpDate) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setParentTaskId(String parentTaskId) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setTenantId(String tenantId) {
    throw UNMODIFIABLE;
  }
}
