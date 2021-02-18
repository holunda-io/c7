package io.holunda.commons.immutables.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.CurrentTimestamp;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableTask.class)
@JsonSerialize(as = ImmutableTask.class)
@SuppressWarnings({"java:S114", "java:S1133"})
interface _Task extends _TaskWithoutSetters, CurrentTimestamp {

  @Override
  String getId();

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
    return getNow().get();
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
  @Deprecated(since = "0.13.0")
  default void delegate(String userId) {
    throw UNMODIFIABLE;
  }

  @Override
  default int getPriority() {
    return PRIORITY_NORMAL;
  }

  @Nullable
  @Override
  String getParentTaskId();

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
@SuppressWarnings({"java:S114", "java:S1133"})
interface _TaskWithoutSetters extends Task {

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
  default void setDescription(String description) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setPriority(int priority) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setOwner(String owner) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setAssignee(String assignee) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setDelegationState(DelegationState delegationState) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setCaseInstanceId(String caseInstanceId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setDueDate(Date dueDate) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setFollowUpDate(Date followUpDate) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setParentTaskId(String parentTaskId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.13.0")
  default void setTenantId(String tenantId) {
    throw UNMODIFIABLE;
  }
}
