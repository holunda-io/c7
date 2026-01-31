package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.AND
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.THEN
import io.toolisticon.testing.jgiven.WHEN
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RepositoryService
import org.camunda.community.rest.itest.stages.*
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

@As("Update Suspension State")
class RepositoryServiceSuspensionStateITest : CamundaRestClientITestBase<RepositoryService, RepositoryServiceActionStage, RepositoryServiceAssertStage>() {

  @Test
  fun `should suspend and activate process definition by key`() {
    val processDefinitionKey = processDefinitionKey()

    GIVEN
      .process_is_deployed(processDefinitionKey)

    WHEN
      .process_definition_is_suspended_by_key(processDefinitionKey)

    THEN
      .process_definition_query_succeeds { processDefinitionQuery, _ ->
        assertThat(
          processDefinitionQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isTrue()
      }
      .process_definition_is_activated_by_key()
      .AND
      .process_definition_query_succeeds { processInstanceQuery, _ ->
        assertThat(
          processInstanceQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isFalse()
      }

  }

  @Test
  fun `should suspend and activate process definition by key with details`() {
    val processDefinitionKey = processDefinitionKey()

    GIVEN
      .process_is_deployed(processDefinitionKey)

    WHEN
      .process_definition_is_suspended_by_key_with_details(processDefinitionKey)

    TimeUnit.SECONDS.sleep(1)

    THEN
      .process_definition_query_succeeds { processDefinitionQuery, _ ->
        assertThat(
          processDefinitionQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isTrue()
      }
      .process_definition_is_activated_by_key_with_details()
      .AND
      .process_definition_query_succeeds { processInstanceQuery, _ ->
        assertThat(
          processInstanceQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isFalse()
      }

  }

  @Test
  fun `should suspend and activate process instance by process definition key`() {
    val processDefinitionKey = processDefinitionKey()

    GIVEN
      .process_is_deployed(processDefinitionKey)

    WHEN
      .process_definition_is_suspended_by_id()

    THEN
      .process_definition_query_succeeds { processDefinitionQuery, _ ->
        assertThat(
          processDefinitionQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isTrue()
      }
      .process_definition_is_activated_by_id()
      .AND
      .process_definition_query_succeeds { processDefinitionQuery, _ ->
        assertThat(
          processDefinitionQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isFalse()
      }

  }

  @Test
  fun `should suspend and activate process instance by process definition key with details`() {
    val processDefinitionKey = processDefinitionKey()

    GIVEN
      .process_is_deployed(processDefinitionKey)

    WHEN
      .process_definition_is_suspended_by_id_with_details()

    TimeUnit.SECONDS.sleep(1)

    THEN
      .process_definition_query_succeeds { processDefinitionQuery, _ ->
        assertThat(
          processDefinitionQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isTrue()
      }
      .process_definition_is_activated_by_id_with_details()
      .AND
      .process_definition_query_succeeds { processDefinitionQuery, _ ->
        assertThat(
          processDefinitionQuery
            .processDefinitionKey(processDefinitionKey)
            .singleResult()
            .isSuspended
        ).isFalse()
      }

  }
}
