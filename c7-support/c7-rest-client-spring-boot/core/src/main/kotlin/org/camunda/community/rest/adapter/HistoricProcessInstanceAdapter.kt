package org.camunda.community.rest.adapter

import org.camunda.bpm.engine.history.HistoricProcessInstance
import org.camunda.community.rest.client.model.HistoricProcessInstanceDto
import org.camunda.community.rest.impl.toDate
import java.util.*

/**
 * Implementation of Camunda API Historic Process Instance backed by a bean.
 */
class HistoricProcessInstanceAdapter(private val historicInstanceBean: HistoricInstanceBean) : HistoricProcessInstance {

  override fun getBusinessKey(): String? = historicInstanceBean.businessKey
  override fun getProcessDefinitionKey(): String? = historicInstanceBean.processDefinitionKey
  override fun getRootProcessInstanceId(): String? = historicInstanceBean.rootProcessInstanceId
  override fun getSuperCaseInstanceId(): String? = historicInstanceBean.superCaseInstanceId
  override fun getProcessDefinitionId(): String = historicInstanceBean.processDefinitionId
  override fun getProcessDefinitionName(): String? = historicInstanceBean.processDefinitionName
  override fun getProcessDefinitionVersion(): Int = historicInstanceBean.processDefinitionVersion
  override fun getStartTime(): Date = historicInstanceBean.startTime
  override fun getEndTime(): Date? = historicInstanceBean.endTime
  override fun getRemovalTime(): Date? = historicInstanceBean.removalTime
  override fun getDurationInMillis(): Long? = historicInstanceBean.durationInMillis
  @Deprecated("Deprecated in Java")
  override fun getEndActivityId(): String? = historicInstanceBean.endActivityId
  override fun getStartUserId(): String? = historicInstanceBean.startUserId
  override fun getStartActivityId(): String? = historicInstanceBean.startActivityId
  override fun getDeleteReason(): String? = historicInstanceBean.deleteReason
  override fun getSuperProcessInstanceId(): String? = historicInstanceBean.superProcessInstanceId
  override fun getCaseInstanceId(): String? = historicInstanceBean.caseInstanceId
  override fun getId(): String = historicInstanceBean.id
  override fun getTenantId(): String? = historicInstanceBean.tenantId
  override fun getState(): String? = historicInstanceBean.state
  override fun getRestartedProcessInstanceId(): String? = historicInstanceBean.restartedProcessInstanceId
}

/**
 * Backing bean for historic process instance.
 */
data class HistoricInstanceBean(
  val id: String,
  val businessKey: String?,
  val processDefinitionKey: String?,
  val processDefinitionId: String,
  val processDefinitionName: String?,
  val processDefinitionVersion: Int,
  val startTime: Date,
  val endTime: Date?,
  val removalTime: Date?,
  val durationInMillis: Long?,
  val endActivityId: String?,
  val startUserId: String?,
  val startActivityId: String?,
  val deleteReason: String?,
  val superProcessInstanceId: String?,
  val rootProcessInstanceId: String?,
  val superCaseInstanceId: String?,
  val caseInstanceId: String?,
  val tenantId: String?,
  val state: String?,
  val restartedProcessInstanceId: String?
) {
  companion object {
    /**
     * Factory method to construct the bean from DTO.
     * @param processInstance: REST representation of historic process instance.
     */
    @JvmStatic
    fun fromHistoricProcessInstanceDto(processInstance: HistoricProcessInstanceDto) =
      HistoricInstanceBean(
        id = processInstance.id!!,
        businessKey = processInstance.businessKey,
        processDefinitionKey = processInstance.processDefinitionKey,
        processDefinitionId = processInstance.processDefinitionId!!,
        processDefinitionName = processInstance.processDefinitionName,
        processDefinitionVersion = processInstance.processDefinitionVersion!!,
        startTime = processInstance.startTime.toDate()!!,
        endTime = processInstance.endTime.toDate(),
        removalTime = processInstance.removalTime.toDate(),
        durationInMillis = processInstance.durationInMillis,
        endActivityId = null,
        startUserId = processInstance.startUserId,
        startActivityId = processInstance.startActivityId,
        deleteReason = processInstance.deleteReason,
        superProcessInstanceId = processInstance.superProcessInstanceId,
        rootProcessInstanceId = processInstance.rootProcessInstanceId,
        superCaseInstanceId = processInstance.superCaseInstanceId,
        caseInstanceId = processInstance.caseInstanceId,
        tenantId = processInstance.tenantId,
        state = when (processInstance.state) {
          HistoricProcessInstanceDto.StateEnum.ACTIVE -> HistoricProcessInstance.STATE_ACTIVE
          HistoricProcessInstanceDto.StateEnum.COMPLETED -> HistoricProcessInstance.STATE_COMPLETED
          HistoricProcessInstanceDto.StateEnum.SUSPENDED -> HistoricProcessInstance.STATE_SUSPENDED
          HistoricProcessInstanceDto.StateEnum.EXTERNALLY_TERMINATED -> HistoricProcessInstance.STATE_EXTERNALLY_TERMINATED
          HistoricProcessInstanceDto.StateEnum.INTERNALLY_TERMINATED -> HistoricProcessInstance.STATE_INTERNALLY_TERMINATED
          else -> throw IllegalStateException("Unknown state for historic process instance")
        },
        restartedProcessInstanceId = processInstance.restartedProcessInstanceId
      )

  }
}
