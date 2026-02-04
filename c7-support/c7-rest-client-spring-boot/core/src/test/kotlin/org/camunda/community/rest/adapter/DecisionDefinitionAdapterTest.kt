package org.camunda.community.rest.adapter

import org.assertj.core.api.Assertions
import org.camunda.community.rest.client.model.DecisionDefinitionDto
import org.junit.jupiter.api.Test

class DecisionDefinitionAdapterTest {
  private val dto = DecisionDefinitionDto()
    .id("id")
    .tenantId("tenantId")
    .key("key")
    .category("category")
    .name("name")
    .version(1)
    .resource("resource")
    .deploymentId("deploymentId")
    .tenantId("tenantId")
    .versionTag("versionTag")
    .historyTimeToLive(1)
    .decisionRequirementsDefinitionId("decisionRequirementsDefinitionId")
    .decisionRequirementsDefinitionKey("decisionRequirementsDefinitionKey")

  @Test
  fun `should delegate`() {
    val bean = DecisionDefinitionBean.fromDto(dto)
    val adapter = DecisionDefinitionAdapter(bean)
    Assertions.assertThat(adapter).usingRecursiveComparison().ignoringFields("decisionDefinitionBean").isEqualTo(bean)
  }

  @Test
  fun `should construct from dto`() {
    val bean = DecisionDefinitionBean.fromDto(dto)
    Assertions.assertThat(bean).usingRecursiveComparison().ignoringFields("resourceName").isEqualTo(dto)
  }
}
