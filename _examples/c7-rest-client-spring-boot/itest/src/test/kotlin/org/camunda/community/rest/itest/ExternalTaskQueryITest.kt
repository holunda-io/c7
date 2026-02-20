package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.AND
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.THEN
import io.toolisticon.testing.jgiven.WHEN
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.Variables.createVariables
import org.camunda.community.rest.itest.stages.CamundaRestClientITestBase
import org.camunda.community.rest.itest.stages.ExternalTaskServiceActionStage
import org.camunda.community.rest.itest.stages.ExternalTaskServiceAssertStage
import org.junit.jupiter.api.Test
import org.springframework.test.annotation.DirtiesContext

@As("Creates process instance query")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class ExternalTaskQueryITest :
  CamundaRestClientITestBase<ExternalTaskService, ExternalTaskServiceActionStage, ExternalTaskServiceAssertStage>() {

  @Test
  fun `find external task by process instance id`() {

    GIVEN
      .process_from_a_resource_is_deployed("test_external_task.bpmn")
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key2", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key3", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )


    WHEN
      .process_waits_in_external_task("topic")

    THEN
      .external_task_query_succeeds { query, _ ->
        assertThat(
          query
            .processInstanceId(THEN.processInstance.id)
            .count()
        ).isEqualTo(1)

       }

  }

  @Test
  fun `find external task by process definition key`() {

    GIVEN
      .process_from_a_resource_is_deployed("test_external_task.bpmn")
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key2", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key3", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )


    WHEN
      .process_waits_in_external_task("topic")

    THEN
      .external_task_query_succeeds { query, _ ->
        assertThat(
          query
            .processDefinitionKey("test_external_task")
            .count()
        ).isEqualTo(2)

      }

  }

  @Test
  fun `find external task by topic name`() {

    GIVEN
      .process_from_a_resource_is_deployed("test_external_task.bpmn")
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key2", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key3", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )


    WHEN
      .process_waits_in_external_task("topic")

    THEN
      .external_task_query_succeeds { query, _ ->

        assertThat(
          query
            .topicName("topic")
            .count()
        ).isEqualTo(2)

        assertThat(
          query
            .active()
            .count()
        ).isEqualTo(2)

        assertThat(
          query
            .notLocked()
            .count()
        ).isEqualTo(2)

        assertThat(
          query
            .withRetriesLeft()
            .list()
        ).hasSize(2)

        assertThat(
          query
            .withRetriesLeft()
            .list()
            .map { it.businessKey }
        ).contains("my-business-key2", "my-business-key3")

      }

  }

  @Test
  fun `find external task sort by id`() {

    GIVEN
      .process_from_a_resource_is_deployed("test_external_task.bpmn")
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key2", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )
      .AND
      .process_is_started_by_key(
        "test_external_task", "my-business-key3", "caseInstanceId2",
        createVariables()
          .putValue("VAR1", "VAL1")
          .putValueTyped("VAR4", Variables.objectValue("My object value").create())
      )


    WHEN
      .process_waits_in_external_task("topic")

    THEN
      .external_task_query_succeeds { query, _ ->

        assertThat(
          query
            .topicName("topic")
            .orderById()
            .desc()
            .count()
        ).isEqualTo(2)

      }

  }

}
