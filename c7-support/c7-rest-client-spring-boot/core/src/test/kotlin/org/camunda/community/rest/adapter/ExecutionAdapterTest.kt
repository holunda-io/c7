package org.camunda.community.rest.adapter

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.rest.adapter.ExecutionAdapter
import org.camunda.community.rest.adapter.ExecutionBean
import org.camunda.community.rest.client.model.ExecutionDto
import org.junit.jupiter.api.Test

class ExecutionAdapterTest {

  private val dto = ExecutionDto()
    .id("id")
    .processInstanceId("processInstanceId")
    .ended(true)
    .tenantId("tenantId")

  @Test
  fun `should delegate`() {
    val executionBean = ExecutionBean.fromExecutionDto(dto)
    val executionAdapter = ExecutionAdapter(executionBean)
    assertThat(executionAdapter.isEnded).isEqualTo(executionBean.ended)
    assertThat(executionAdapter.isSuspended).isEqualTo(executionBean.suspended)
    assertThat(executionAdapter.processInstanceId).isEqualTo(executionBean.processInstanceId)
    assertThat(executionAdapter.tenantId).isEqualTo(executionBean.tenantId)
  }

  @Test
  fun `should construct from dto`() {
    val bean = ExecutionBean.fromExecutionDto(dto)

    assertThat(dto.id).isEqualTo(bean.id)
    assertThat(dto.ended!!).isEqualTo(bean.ended)
    assertThat(dto.processInstanceId).isEqualTo(bean.processInstanceId)
    assertThat(dto.tenantId).isEqualTo(bean.tenantId)

  }
}
