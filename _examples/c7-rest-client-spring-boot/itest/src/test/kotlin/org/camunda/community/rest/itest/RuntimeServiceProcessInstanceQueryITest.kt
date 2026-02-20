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
import org.springframework.test.annotation.DirtiesContext

@As("Creates process instance query")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class RuntimeServiceProcessInstanceQueryITest :
  CamundaRestClientITestBase<RuntimeService, RuntimeServiceActionStage, RuntimeServiceAssertStage>() {

  @Test
  fun `find process started by id`() {
    val processDefinitionKey = processDefinitionKey()
    val key1 = "businessKey1"
    val vars1 = createVariables().putValue("VAR1", "value1")
    val key2 = "businessKey2"
    val vars2 = createVariables().putValue("VAR1", "value2")

    GIVEN
      .no_deployment_exists()
      .and()
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .apply {
        remoteService.startProcessInstanceById(GIVEN.processDefinition.id, key1, vars1)
        remoteService.startProcessInstanceById(GIVEN.processDefinition.id, key2, vars2)
      }

    THEN
      .process_instance_query_succeeds { query, _ ->
        assertThat(
          query
            .processDefinitionKey(processDefinitionKey)
            .count()
        ).isEqualTo(2)

        assertThat(
          query
            .processInstanceBusinessKey(key1)
            .count()
        ).isEqualTo(1)

        assertThat(
          query
            .processInstanceBusinessKey(key1)
            .singleResult()
            .businessKey
        ).isEqualTo(key1)

        assertThat(
          query
            .variableValueEquals("VAR1", "value1")
            .singleResult()
            .businessKey
        ).isEqualTo(key1)

        assertThat(
          query
            .matchVariableNamesIgnoreCase()
            .variableValueEquals("var1", "value1")
            .singleResult()
            .businessKey
        ).isEqualTo(key1)

        assertThat(
          query
            .matchVariableNamesIgnoreCase()
            .variableValueEquals("var1", "value1")
            .singleResult().id
        ).isEqualTo(
          query
            .matchVariableNamesIgnoreCase()
            .variableValueEquals("var1", "value1")
            .list()[0].id
        )

      }

  }

  @Test
  fun `find process sorted by business key`() {
    val processDefinitionKey = processDefinitionKey()
    val key1 = "businessKey1"
    val vars1 = createVariables().putValue("VAR1", "value1")
    val key2 = "businessKey2"
    val vars2 = createVariables().putValue("VAR1", "value2")
    val key3 = "businessKey3"

    GIVEN
      .no_deployment_exists()
      .and()
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .apply {
        remoteService.startProcessInstanceById(GIVEN.processDefinition.id, key3, vars1)
        remoteService.startProcessInstanceById(GIVEN.processDefinition.id, key2, vars2)
        remoteService.startProcessInstanceById(GIVEN.processDefinition.id, key1, vars2)
        remoteService.startProcessInstanceById(GIVEN.processDefinition.id, key2, vars2)
      }

    THEN
      .process_instance_query_succeeds { query, _ ->
        assertThat(
          query
            .orderByBusinessKey().asc()
            .list()
            .map { it.businessKey }
        ).containsExactly(key1, key2, key2, key3)
      }
      .and()
      .process_instance_query_succeeds { query, _ ->
        assertThat(
          query
            .orderByBusinessKey().desc()
            .list()
            .map { it.businessKey }
        ).containsExactly(key3, key2, key2, key1)
      }
  }

  @Test
  fun `find process sorted by process definition key and business key`() {
    val processDefinitionKey1 = "processDefinitionKey1"
    val processDefinitionKey2 = "processDefinitionKey2"
    val key1 = "businessKey1"
    val key2 = "businessKey2"
    val key3 = "businessKey3"

    GIVEN.no_deployment_exists()

    val processDefinition1 = GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey1)
      .processDefinition.id

    val processDefinition2 = GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey2)
      .processDefinition.id

    WHEN
      .apply {
        remoteService.startProcessInstanceByKey(processDefinitionKey1, key3)
        remoteService.startProcessInstanceByKey(processDefinitionKey2, key3)
        remoteService.startProcessInstanceByKey(processDefinitionKey1, key1)
        remoteService.startProcessInstanceByKey(processDefinitionKey1, key2)
        remoteService.startProcessInstanceByKey(processDefinitionKey2, key2)
        remoteService.startProcessInstanceByKey(processDefinitionKey2, key1)
        remoteService.startProcessInstanceByKey(processDefinitionKey2, key3)
      }

    THEN
      .process_instance_query_succeeds { query, _ ->
        val result = query
          .orderByBusinessKey().asc()
          .orderByProcessDefinitionKey().desc()
          .list()
        assertThat(
          result.map { it.businessKey }
        ).containsExactly(key1, key1, key2, key2, key3, key3, key3)
        assertThat(
          result.map { it.processDefinitionId }
        ).containsExactly(processDefinition2, processDefinition1, processDefinition2, processDefinition1, processDefinition2, processDefinition2, processDefinition1)
      }
  }


  @Test
  fun `find process by single process instance id`() {
    val processDefinitionKey = "processDefinitionKey"
    val key1 = "businessKey1"
    val key2 = "businessKey2"
    val key3 = "businessKey3"

    GIVEN.no_deployment_exists()

    GIVEN
      .process_with_user_task_is_deployed(processDefinitionKey)

    WHEN
      .apply {
        remoteService.startProcessInstanceByKey(processDefinitionKey, key1)
        remoteService.startProcessInstanceByKey(processDefinitionKey, key2)
        processInstance = remoteService.startProcessInstanceByKey(processDefinitionKey, key3)
      }

    THEN
      .process_instance_query_succeeds { query, _ ->
        val result = query
          .processInstanceId(GIVEN.processInstance.id)
          .count()
        assertThat(
          result
        ).isEqualTo(1)
      }
  }

}
