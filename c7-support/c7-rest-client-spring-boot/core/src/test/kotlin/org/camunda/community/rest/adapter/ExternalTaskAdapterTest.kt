package org.camunda.community.rest.adapter

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.rest.client.model.ExternalTaskDto
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class ExternalTaskAdapterTest {

  private val dto = ExternalTaskDto()
    .id("id")
    .processInstanceId("processInstanceId")
    .tenantId("tenantId")
    .activityId("activityId")
    .activityInstanceId("activityInstanceId")
    .businessKey("businessKey")
    .errorMessage("errorMessage")
    .executionId("executionId")
    .lockExpirationTime(OffsetDateTime.now())
    .priority(1L)
    .processDefinitionId("processDefinitionId")
    .processDefinitionKey("processDefinitionKey")
    .processDefinitionVersionTag("processDefinitionVersionTag")
    .retries(1)
    .suspended(true)
    .topicName("topicName")
    .workerId("workerId")

  @Test
  fun `should delegate`() {
    val externalTaskBean = ExternalTaskBean.fromDto(dto)
    val externalTaskAdapter = ExternalTaskAdapter(externalTaskBean)
    assertThat(externalTaskAdapter).usingRecursiveComparison().ignoringFields("externalTaskBean").isEqualTo(externalTaskBean)
  }

  @Test
  fun `should construct from dto`() {
    val bean = ExternalTaskBean.fromDto(dto)
    assertThat(bean).usingRecursiveComparison().ignoringFields("lockExpirationTime").isEqualTo(dto)
    assertThat(bean.lockExpirationTime).isEqualTo(dto.lockExpirationTime.toInstant())
  }
}
