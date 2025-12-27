package org.camunda.community.rest.adapter

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.rest.client.model.IdentityLinkDto
import org.junit.jupiter.api.Test

class IdentityLinkAdapterTest {

  private val dto = IdentityLinkDto()
    .userId("userId")
    .groupId("groupId")
    .type("candidate")

  @Test
  fun `should delegate`() {
    val bean = IdentityLinkBean.fromDto("taskId", dto)
    val adapter = IdentityLinkAdapter(bean)
    assertThat(adapter).usingRecursiveComparison().ignoringFields("identityLinkBean").isEqualTo(bean)
  }

  @Test
  fun `should construct from dto`() {
    val bean = IdentityLinkBean.fromDto("taskId", dto)
    assertThat(bean).usingRecursiveComparison().ignoringFields("processDefinitionId", "tenantId", "id", "taskId").isEqualTo(dto)
  }
}
