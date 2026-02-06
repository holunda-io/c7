package org.camunda.community.rest.adapter

import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.community.rest.client.model.ProcessInstanceDto
import org.camunda.community.rest.client.model.ProcessInstanceWithVariablesDto

/**
 * Implementation of Camunda API Process Instance backed by a bean.
 */
class ProcessInstanceAdapter(private val instanceBean: InstanceBean) : ProcessInstance {

  override fun getProcessInstanceId(): String? = when (instanceBean.type) {
    InstanceType.CASE -> null
    InstanceType.PROCESS -> instanceBean.instanceId
  }

  override fun getBusinessKey(): String? = instanceBean.businessKey

  override fun getRootProcessInstanceId(): String? =
    when (instanceBean.type) {
      InstanceType.PROCESS -> instanceBean.rootProcessInstanceId
      InstanceType.CASE -> null
    }

  override fun getProcessDefinitionId(): String? =
    when (instanceBean.type) {
      InstanceType.PROCESS -> instanceBean.processDefinitionId
      InstanceType.CASE -> null
    }

  override fun isSuspended(): Boolean = instanceBean.suspended

  override fun getCaseInstanceId(): String? =
    when (instanceBean.type) {
      InstanceType.PROCESS -> null
      InstanceType.CASE -> instanceBean.instanceId
    }


  override fun isEnded(): Boolean = instanceBean.ended
  override fun getId(): String = instanceBean.id
  override fun getTenantId(): String? = instanceBean.tenantId
  override fun getProcessDefinitionKey(): String? = instanceBean.processDefinitionKey

}

/**
 * Backing bean for process instance.
 */
data class InstanceBean(
  val id: String,
  val ended: Boolean,
  val suspended: Boolean,
  val type: InstanceType,
  val instanceId: String,
  val businessKey: String? = null,
  val tenantId: String? = null,
  val processDefinitionId: String? = null,
  val processDefinitionKey: String? = null,
  val rootProcessInstanceId: String? = null
) {
  companion object {
    /**
     * Factory method to construct the bean from DTO.
     * @param processInstance: REST representation of process instance.
     */
    @Suppress("DEPRECATION")
    @JvmStatic
    fun fromProcessInstanceDto(processInstance: ProcessInstanceDto) =
      InstanceBean(
        id = processInstance.id!!,
        ended = processInstance.ended!!,
        suspended = processInstance.suspended!!,
        businessKey = processInstance.businessKey,
        tenantId = processInstance.tenantId,
        type = if (processInstance.caseInstanceId != null) {
          InstanceType.CASE
        } else {
          InstanceType.PROCESS
        },
        instanceId = processInstance.caseInstanceId ?: processInstance.id,
        processDefinitionId = processInstance.definitionId,
        processDefinitionKey = processInstance.definitionKey
      )

    /**
     * Factory method to construct the bean from DTO.
     * @param processInstance: REST representation of process instance.
     */
    @JvmStatic
    @Suppress("DEPRECATION")
    fun fromProcessInstanceDto(processInstance: ProcessInstanceWithVariablesDto) =
      InstanceBean(
        id = processInstance.id!!,
        ended = processInstance.ended!!,
        suspended = processInstance.suspended!!,
        businessKey = processInstance.businessKey,
        tenantId = processInstance.tenantId,
        type = if (processInstance.caseInstanceId != null) {
          InstanceType.CASE
        } else {
          InstanceType.PROCESS
        },
        instanceId = processInstance.caseInstanceId ?: processInstance.id,
        processDefinitionId = processInstance.definitionId
      )
  }
}

/**
 * Instance type to cope with different ids.
 */
enum class InstanceType {
  /**
   * Process instance.
   */
  PROCESS,
  /**
   * Case instance.
   */
  CASE
}
