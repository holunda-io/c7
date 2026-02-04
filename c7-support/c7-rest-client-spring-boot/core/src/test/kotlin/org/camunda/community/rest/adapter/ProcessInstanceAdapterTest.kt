package org.camunda.community.rest.adapter

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.rest.adapter.InstanceBean
import org.camunda.community.rest.adapter.InstanceType
import org.camunda.community.rest.adapter.ProcessInstanceAdapter
import org.camunda.community.rest.client.model.ProcessInstanceDto
import org.junit.jupiter.api.Test

class ProcessInstanceAdapterTest {

  private val processBean = InstanceBean(
    id = "id",
    ended = false,
    suspended = false,
    type = InstanceType.PROCESS,
    instanceId = "instanceId",
    businessKey = "businessKey",
    tenantId = "tenantId",
    processDefinitionId = "processDefId",
    rootProcessInstanceId = "root"
  )
  private val caseBean = InstanceBean(
    id = "id",
    ended = false,
    suspended = false,
    type = InstanceType.CASE,
    instanceId = "instanceId",
    businessKey = "businessKey",
    tenantId = "tenantId",
    processDefinitionId = "processDefId",
    rootProcessInstanceId = "root"
  )

  @Test
  fun `should construct process instance`() {
    val adapter = ProcessInstanceAdapter(processBean)
    assertThat(adapter.businessKey).isEqualTo(processBean.businessKey)
    assertThat(adapter.id).isEqualTo(processBean.id)
    assertThat(adapter.processDefinitionId).isEqualTo(processBean.processDefinitionId)
    assertThat(adapter.rootProcessInstanceId).isEqualTo(processBean.rootProcessInstanceId)
    assertThat(adapter.tenantId).isEqualTo(processBean.tenantId)
    assertThat(adapter.isEnded).isEqualTo(processBean.ended)
    assertThat(adapter.isSuspended).isEqualTo(processBean.suspended)
    assertThat(adapter.processInstanceId).isEqualTo(processBean.instanceId)
    assertThat(adapter.caseInstanceId).isNull()
  }

  @Test
  fun `should construct case instance`() {
    val adapter = ProcessInstanceAdapter(caseBean)
    assertThat(adapter.businessKey).isEqualTo(caseBean.businessKey)
    assertThat(adapter.id).isEqualTo(caseBean.id)
    assertThat(adapter.tenantId).isEqualTo(caseBean.tenantId)
    assertThat(adapter.isEnded).isEqualTo(caseBean.ended)
    assertThat(adapter.isSuspended).isEqualTo(caseBean.suspended)
    assertThat(adapter.caseInstanceId).isEqualTo(caseBean.instanceId)
    assertThat(adapter.rootProcessInstanceId).isNull()
    assertThat(adapter.processDefinitionId).isNull()
    assertThat(adapter.processInstanceId).isNull()
  }

  @Test
  fun `from dto`() {
    val dto = ProcessInstanceDto()
      .id("id")
      .businessKey("businessKey")
      .tenantId("tenantId")
      .ended(true)
      .suspended(false)
      .caseInstanceId(null)
    val bean = InstanceBean.fromProcessInstanceDto(dto)

    assertThat(dto.businessKey).isEqualTo(bean.businessKey)
    assertThat(dto.id).isEqualTo(bean.id)
    assertThat(dto.tenantId).isEqualTo(bean.tenantId)
    @Suppress("DEPRECATION")
    assertThat(dto.ended!!).isEqualTo(bean.ended)
    assertThat(dto.suspended!!).isEqualTo(bean.suspended)
    assertThat(dto.caseInstanceId).isNull()
  }
}
