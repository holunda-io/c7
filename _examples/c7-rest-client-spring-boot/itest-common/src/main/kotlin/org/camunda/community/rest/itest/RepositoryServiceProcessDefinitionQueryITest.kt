package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.AND
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.THEN
import io.toolisticon.testing.jgiven.WHEN
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RepositoryService
import org.camunda.community.rest.itest.stages.CamundaRestClientITestBase
import org.camunda.community.rest.itest.stages.RepositoryServiceActionStage
import org.camunda.community.rest.itest.stages.RepositoryServiceAssertStage
import org.junit.jupiter.api.Test
import org.springframework.test.annotation.DirtiesContext

@As("Creates process definition query")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class RepositoryServiceProcessDefinitionQueryITest :
  CamundaRestClientITestBase<RepositoryService, RepositoryServiceActionStage, RepositoryServiceAssertStage>() {

  @Test
  fun `should find auto deployed processes only`() {
    GIVEN
      .no_deployment_exists()
    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .count()
        ).isEqualTo(0)
      }
  }

  @Test
  fun `should find deployed processes by process definition key`() {
    val processDefinitionKey = processDefinitionKey()
    val tag = "1.0.0"
    val another = processDefinitionKey()

    GIVEN
      .no_deployment_exists()

    WHEN
      .process_is_deployed(processDefinitionKey, tag)
      .AND
      .process_is_deployed(processDefinitionKey)
      .AND
      .process_is_deployed(another)

    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .processDefinitionKey(processDefinitionKey)
            .count()
        ).isEqualTo(2)

        assertThat(
          query
            .processDefinitionKey(processDefinitionKey)
            .latestVersion()
            .count()
        ).isEqualTo(1)
      }
  }

  @Test
  fun `should find deployed processes by process definition key and version`() {
    val processDefinitionKey = processDefinitionKey()
    val tag = "1.0.0"

    GIVEN
      .no_deployment_exists()

    WHEN
      .process_is_deployed(processDefinitionKey, tag)

    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .processDefinitionKey(processDefinitionKey)
            .versionTag(tag)
            .list()
            .size
        ).isEqualTo(1)

        assertThat(
          query
            .processDefinitionKey(processDefinitionKey)
            .versionTag("non-existent")
            .list()
        ).isEmpty()
      }
  }

  @Test
  fun `should sort deployed processes by process definition key`() {
    val processDefinitionKey1 = "AAA"
    val processDefinitionKey2 = "BBB"
    val processDefinitionKey3 = "CCC"

    GIVEN
      .no_deployment_exists()

    WHEN
      .process_is_deployed(processDefinitionKey1)
      .process_is_deployed(processDefinitionKey2)
      .process_is_deployed(processDefinitionKey3)

    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .orderByProcessDefinitionKey().desc()
            .list()
            .map { it.key }
        ).containsExactly(processDefinitionKey3, processDefinitionKey2, processDefinitionKey1)

      }
  }

}
