package org.camunda.community.rest.adapter

import org.camunda.bpm.engine.runtime.Execution
import org.camunda.community.rest.client.model.ExecutionDto

/**
 * Implementation of Camunda API Execution backed by a bean.
 */
class ExecutionAdapter(private val executionBean: ExecutionBean) : Execution {
  override fun getProcessInstanceId(): String = executionBean.processInstanceId
  override fun isEnded(): Boolean = executionBean.ended
  override fun getId(): String = executionBean.id
  override fun isSuspended(): Boolean = executionBean.suspended
  override fun getTenantId(): String? = executionBean.tenantId
  override fun getProcessDefinitionKey(): String? {
    throw UnsupportedOperationException("Process definition key not supported via REST")
  }
}

/**
 * Backing bean for the execution.
 */
data class ExecutionBean(
  val id: String,
  val processInstanceId: String,
  val ended: Boolean,
  val suspended: Boolean,
  val tenantId: String? = null
) {
  companion object {
    /**
     * Constructs the bean from Execution DTO.
     * @param dto: REST representation of the execution.
     */
    @JvmStatic
    fun fromExecutionDto(dto: ExecutionDto) =
      ExecutionBean(
        id = dto.id,
        processInstanceId = dto.processInstanceId,
        ended = dto.ended,
        suspended = false,
        tenantId = dto.tenantId
      )
  }
}
