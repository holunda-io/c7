package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.CASE_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.CASE_EXECUTION;
import static io.holunda.commons.immutables._Fixtures.CASE_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.DATE_NOW;
import static io.holunda.commons.immutables._Fixtures.DATE_TOMORROW;
import static io.holunda.commons.immutables._Fixtures.EXECUTION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TASK;
import static io.holunda.commons.immutables._Fixtures.TASK_DEFINITION_KEY;
import static io.holunda.commons.immutables._Fixtures.TASK_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.USER_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;

public class TaskTest extends _BasicImmutableTest<ImmutableTask> {

  public TaskTest() {
    super(ImmutableTask.class);
  }

  @Override
  public void factory_method() {
    final ImmutableTask dto = createDto();

    Assertions.assertThat(dto).hasId(TASK_ID);
    Assertions.assertThat(dto).hasName(TASK.getName());
    Assertions.assertThat(dto).hasDescription(TASK.getDescription());
    Assertions.assertThat(dto).hasPriority(Task.PRIORITY_MAXIMUM);
    Assertions.assertThat(dto).hasOwner(USER_ID);
    Assertions.assertThat(dto).hasAssignee(USER_ID);
    Assertions.assertThat(dto).hasDelegationState(DelegationState.PENDING);
    Assertions.assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    Assertions.assertThat(dto).hasExecutionId(EXECUTION_ID);
    Assertions.assertThat(dto).hasProcessDefinitionId(PROCESS_DEFINITION_ID);
    Assertions.assertThat(dto).hasCaseInstanceId(CASE_INSTANCE_ID);
    Assertions.assertThat(dto).hasCaseExecutionId(CASE_EXECUTION.getId());
    Assertions.assertThat(dto).hasCaseDefinitionId(CASE_DEFINITION_ID);
    Assertions.assertThat(dto).hasCreateTime(DATE_NOW);
    Assertions.assertThat(dto).hasDueDate(DATE_TOMORROW);
    Assertions.assertThat(dto).hasFollowUpDate(DATE_TOMORROW);
    Assertions.assertThat(dto).hasTaskDefinitionKey(TASK_DEFINITION_KEY);
    Assertions.assertThat(dto).hasParentTaskId(null);
    Assertions.assertThat(dto).isSuspended();
    Assertions.assertThat(dto).hasFormKey(TASK.getFormKey());
    Assertions.assertThat(dto).hasTenantId(TENANT_ID);
  }

  @Override
  public void create_minimal_dto() {
    ImmutableTask dto = ImmutableTask.builder()
      .id(uuid())
      .executionId(uuid())
      .taskDefinitionKey("task")
      .build();
  }

  @Override
  ImmutableTask createDto() {
    return CamundaImmutables.task(TASK);
  }
}
