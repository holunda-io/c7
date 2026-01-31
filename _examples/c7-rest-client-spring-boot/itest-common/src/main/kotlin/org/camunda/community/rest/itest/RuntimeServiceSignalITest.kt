package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.AND
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.THEN
import io.toolisticon.testing.jgiven.WHEN
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.variable.Variables.createVariables
import org.camunda.community.rest.itest.stages.CamundaRestClientITestBase
import org.camunda.community.rest.itest.stages.RuntimeServiceActionStage
import org.camunda.community.rest.itest.stages.RuntimeServiceAssertStage
import org.junit.jupiter.api.Test


@As("Trigger Signal")
class RuntimeServiceSignalITest : CamundaRestClientITestBase<RuntimeService, RuntimeServiceActionStage, RuntimeServiceAssertStage>() {

  @Test
  fun `should signal waiting instance`() {
    val processDefinitionKey = processDefinitionKey()
    val signalName = "trigger1"
    val userTaskId = "user-task"
    GIVEN
      .no_deployment_exists()
      .AND
      .process_with_intermediate_signal_catch_event_is_deployed(processDefinitionKey, userTaskId, signalName)
      .AND
      .process_is_started_by_key(processDefinitionKey)
      .AND
      .execution_is_waiting_for_signal()

    WHEN
      .remoteService
      .signal(GIVEN.execution.id)

    THEN
      .process_instance_exists(processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isNull()
        assertThat(
          stage.remoteService.createProcessInstanceQuery()
            .processInstanceId(instance.id)
            .activityIdIn(userTaskId)
            .singleResult()
        ).isNotNull
      }
  }

  @Test
  fun `should signal waiting instance and set variables`() {
    val processDefinitionKey = processDefinitionKey()
    val signalName = "trigger2"
    val userTaskId = "user-task"
    GIVEN
      .no_deployment_exists()
      .AND
      .process_with_intermediate_signal_catch_event_is_deployed(processDefinitionKey, userTaskId, signalName)
      .AND
      .process_is_started_by_key(processDefinitionKey, "my-business-key1", "caseInstanceId1", createVariables().putValue("VAR1", "VAL1"))
      .AND
      .execution_is_waiting_for_signal()

    WHEN
      .remoteService
      .signal(GIVEN.execution.id, createVariables().putValue("VAR2", "VAL2"))

    THEN
      .process_instance_exists(processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isEqualTo("my-business-key1")
        assertThat(instance.caseInstanceId).isEqualTo("caseInstanceId1")
        assertThat(
          stage.remoteService.createProcessInstanceQuery()
            .processInstanceId(instance.id)
            .activityIdIn(userTaskId)
            .singleResult()
        ).isNotNull
        assertThat(
          stage.remoteService.getVariables(instance.id, listOf("VAR1", "VAR2"))
        ).containsValues("VAL1", "VAL2")

      }
  }

  @Test
  fun `should signal waiting instance by execution id and signal name`() {
    val processDefinitionKey = processDefinitionKey()
    val signalName = "trigger3"
    val userTaskId = "user-task"
    GIVEN
      .no_deployment_exists()
      .AND
      .process_with_intermediate_signal_catch_event_is_deployed(processDefinitionKey, userTaskId, signalName)
      .AND
      .process_is_started_by_key(processDefinitionKey, "my-business-key1", "caseInstanceId1", createVariables().putValue("VAR1", "VAL1"))
      .AND
      .execution_is_waiting_for_signal()

    WHEN
      .remoteService
      .signal(GIVEN.execution.id, signalName, null, createVariables().putValue("VAR2", "VAL2"))

    THEN
      .process_instance_exists(processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isEqualTo("my-business-key1")
        assertThat(instance.caseInstanceId).isEqualTo("caseInstanceId1")
        assertThat(
          stage.remoteService.createProcessInstanceQuery()
            .processInstanceId(instance.id)
            .activityIdIn(userTaskId)
            .singleResult()
        ).isNotNull
        assertThat(
          stage.remoteService.getVariables(instance.id, listOf("VAR1", "VAR2"))
        ).containsValues("VAL1", "VAL2")
      }
  }
}
