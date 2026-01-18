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

@As("Creates execution query")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class RuntimeServiceExecutionQueryITest :
  CamundaRestClientITestBase<RuntimeService, RuntimeServiceActionStage, RuntimeServiceAssertStage>() {

  @Test
  fun `find execution by process started by id`() {
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
      .process_is_started_by_key(processDefinitionKey, key1, null, vars1)
      .and()
      .process_is_started_by_key(processDefinitionKey, key2, null, vars2)

    THEN
      .execution_query_succeeds { query, _ ->
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
            .executionId(THEN.processInstance!!.id)
            .count()
        ).isEqualTo(1)

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

}
