package org.camunda.community.rest.impl.query

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.rest.client.api.DecisionDefinitionApiClient
import org.camunda.community.rest.client.model.CountResultDto
import org.camunda.community.rest.client.model.DecisionDefinitionDto
import org.junit.jupiter.api.Test

import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.ResponseEntity
import java.util.*

class DelegatingDecisionDefinitionQueryTest {

  val decisionDefinitionApiClient = mock<DecisionDefinitionApiClient>()

  val query = DelegatingDecisionDefinitionQuery(
    decisionDefinitionApiClient
  ).apply {
    this.decisionDefinitionId("id")
    this.decisionDefinitionIdIn("ids")
    this.decisionDefinitionCategory("category")
    this.decisionDefinitionCategoryLike("categoryLike")
    this.decisionDefinitionName("name")
    this.decisionDefinitionNameLike("nameLike")
    this.deploymentId("deploymentId")
    this.deployedAfter(Date())
    this.deployedAt(Date())
    this.decisionDefinitionKey("key")
    this.decisionDefinitionKeyLike("keyLike")
    this.decisionDefinitionResourceName("resourceName")
    this.decisionDefinitionResourceNameLike("resourceNameLike")
    this.decisionDefinitionVersion(1)
    this.decisionRequirementsDefinitionId("decisionRequirementsDefinitionId")
    this.decisionRequirementsDefinitionKey("decisionRequirementsDefinitionKey")
    this.withoutDecisionRequirementsDefinition()
    this.versionTag("versionTag")
    this.versionTagLike("versionTagLike")
    this.tenantIdIn("tenantId")
    this.orderByDecisionDefinitionId().asc()
  }

  @Test
  fun listPage() {
    whenever(
      decisionDefinitionApiClient.getDecisionDefinitions(
        any(), any(), eq(1), eq(10), any(), any(),
        any(), any(), any(), any(), any(), any(), any(), any(),
        any(), any(), any(), any(), any(), any(),
        any(), any(), any(), any(), any(),
        any(), any()
      )
    ).thenReturn(
      ResponseEntity.ok(listOf(DecisionDefinitionDto().id("decisionDefinitionId").name("name").version(1)))
    )
    val result = query.listPage(1, 10)
    assertThat(result).hasSize(1)
  }

  @Test
  fun count() {
    whenever(
      decisionDefinitionApiClient.getDecisionDefinitionsCount(
        any(), any(), any(), any(), any(), any(), any(),
        any(), any(), any(), any(), any(), any(), any(), any(),
        any(), any(), any(), any(), any(),
        any(), any(), any()
      )
    ).thenReturn(
      ResponseEntity.ok(CountResultDto().count(5))
    )
    val result = query.count()
    assertThat(result).isEqualTo(5)
  }

}
