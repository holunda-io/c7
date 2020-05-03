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

  @Override
  default void setDescription(String description) {
    throw UNMODIFIABLE;
  }

  @Override
  default void setPriority(int priority) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getOwner();

  @Override
  default void setOwner(String owner) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  String getAssignee();

  @Override
  default void setAssignee(String assignee) {
    throw UNMODIFIABLE;
  }

  @Override
  DelegationState getDelegationState();

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

  @Override
  default void setDueDate(Date dueDate) {
    throw UNMODIFIABLE;
  }

  @Override
  @Nullable
  Date getFollowUpDate();

  @Override
  default void setFollowUpDate(Date followUpDate) {
    throw UNMODIFIABLE;
  }

  @Override
  default void delegate(String userId) {
    throw UNMODIFIABLE;
  }

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

  @Override
  default void setTenantId(String tenantId) {
    throw UNMODIFIABLE;
  }
}
