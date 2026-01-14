package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
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

@As("Start Process By Key")
class RuntimeServiceStartProcessByKeyITest :
  CamundaRestClientITestBase<RuntimeService, RuntimeServiceActionStage, RuntimeServiceAssertStage>() {

  @Test
  fun `should start process by key`() {
    val processDefinitionKey = processDefinitionKey()
    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)
    WHEN
      .remoteService
      .startProcessInstanceByKey(processDefinitionKey)

    THEN
      .process_instance_exists(processDefinitionKey = processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isNull()
        assertThat(stage.remoteService.getVariables(instance.id)).isEmpty()
      }
  }

  @Test
  fun `should start process by key with business key`() {
    val processDefinitionKey = processDefinitionKey()
    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .remoteService
      .startProcessInstanceByKey(processDefinitionKey, "businessKey")

    THEN
      .process_instance_exists(processDefinitionKey = processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isEqualTo("businessKey")
        assertThat(stage.remoteService.getVariables(instance.id)).isEmpty()
      }
  }

  @Test
  fun `should start process by key with business key and variables`() {
    val processDefinitionKey = processDefinitionKey()
    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .remoteService
      .startProcessInstanceByKey(processDefinitionKey, "businessKey", createVariables().putValue("VAR_NAME", "var value"))

    THEN
      .process_instance_exists(processDefinitionKey = processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isEqualTo("businessKey")
        assertThat(stage.remoteService.getVariables(instance.id)).isNotEmpty
        assertThat(stage.remoteService.getVariable(instance.id, "VAR_NAME")).isEqualTo("var value")
      }
  }

  @Test
  fun `should start process by key with business key and case instance id`() {
    val processDefinitionKey = processDefinitionKey()
    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .remoteService
      .startProcessInstanceByKey(processDefinitionKey, "businessKey", "caseInstanceId")

    THEN
      .process_instance_exists(processDefinitionKey = processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isEqualTo("businessKey")
        assertThat(instance.caseInstanceId).isEqualTo("caseInstanceId")
        assertThat(stage.remoteService.getVariables(instance.id)).isEmpty()
      }
  }


  @Test
  fun `should start process by key with business key, case instance id and variables`() {
    val processDefinitionKey = processDefinitionKey()
    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .remoteService
      .startProcessInstanceByKey(processDefinitionKey, "businessKey", "caseInstanceId", createVariables().putValue("VAR_NAME", "var value"))

    THEN
      .process_instance_exists(processDefinitionKey = processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isEqualTo("businessKey")
        assertThat(instance.caseInstanceId).isEqualTo("caseInstanceId")
        assertThat(stage.remoteService.getVariables(instance.id)).isNotEmpty
        assertThat(stage.remoteService.getVariable(instance.id, "VAR_NAME")).isEqualTo("var value")
      }
  }

  @Test
  fun `should start process by key with variables`() {
    val processDefinitionKey = processDefinitionKey()
    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .remoteService
      .startProcessInstanceByKey(processDefinitionKey, createVariables().putValue("VAR_NAME", "var value"))

    THEN
      .process_instance_exists(processDefinitionKey = processDefinitionKey) { instance, stage ->
        assertThat(instance.businessKey).isNull()
        assertThat(stage.remoteService.getVariables(instance.id)).isNotEmpty
        assertThat(stage.remoteService.getVariable(instance.id, "VAR_NAME")).isEqualTo("var value")
      }
  }
}
