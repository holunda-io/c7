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
class RepositoryServiceDeploymentITest :
  CamundaRestClientITestBase<RepositoryService, RepositoryServiceActionStage, RepositoryServiceAssertStage>() {

  @Test
  fun `should deploy processes successfully`() {
    GIVEN
      .no_deployment_exists()
    WHEN
      .process_definitions_are_deployed("testdeployment", "test")
    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .count()
        ).isEqualTo(2)
        assertThat(
          query.list().map { it.key }
        ).containsAll(listOf("test", "process_messaging"))
      }
  }


  @Test
  fun `should find deployments with query`() {
    GIVEN
      .no_deployment_exists()
    WHEN
      .process_is_deployed("test", deploymentName = "deployment1")
      .process_is_deployed("test2", deploymentName = "deployment2")
    THEN
      .deployment_query_succeeds { query, _ ->
        assertThat(
          query
            .count()
        ).isEqualTo(2)
        assertThat(
          query.list().map { it.name }
        ).containsAll(listOf("deployment1", "deployment2"))
      }
  }

  @Test
  fun `should sort deployments by name`() {
    GIVEN
      .no_deployment_exists()
    WHEN
      .process_is_deployed("test", deploymentName = "BBB")
      .process_is_deployed("test2", deploymentName = "AAA")
      .process_is_deployed("test3", deploymentName = "CCC")
    THEN
      .deployment_query_succeeds { query, _ ->
        assertThat(
          query
            .orderByDeploymentName().asc()
            .list()
            .map { it.name }
        ).containsExactly("AAA", "BBB", "CCC")
      }
  }

  @Test
  fun `should suspend process definition`() {
    GIVEN
      .no_deployment_exists()
      .and()
      .process_is_deployed("test")
    WHEN
      .process_definition_is_suspended_by_key("test")
    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .count()
        ).isEqualTo(1)
        assertThat(
          query.singleResult().isSuspended
        ).isTrue()
      }
  }

  @Test
  fun `should deploy only once if enableDuplicateFiltering is enabled`() {
    GIVEN
      .no_deployment_exists()
    WHEN
      .process_definitions_are_deployed("testdeployment", "test")
      .AND
      .process_definitions_are_deployed("testdeployment", "test", enableDuplicateFiltering = true)
    THEN
      .process_definition_query_succeeds { query, _ ->
        assertThat(
          query
            .latestVersion()
            .count()
        ).isEqualTo(2)
        assertThat(
          query.latestVersion().list().map { it.key }
        ).containsAll(listOf("test", "process_messaging"))
        assertThat(
          // process from classpath is not duplicated, but the one that is generated via API as IDs are random
          query.latestVersion().list().map { it.version }
        ).containsExactly(1, 2)
      }
  }

}
