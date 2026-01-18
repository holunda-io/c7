

package org.camunda.community.rest.adapter

import org.camunda.bpm.engine.repository.DecisionDefinition
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.community.rest.client.model.DecisionDefinitionDto
import org.camunda.community.rest.client.model.ProcessDefinitionDto

/**
 * Implementation of decision definition delegating to a simple bean.
 */
class DecisionDefinitionAdapter(private val decisionDefinitionBean: DecisionDefinitionBean) : DecisionDefinition {
  override fun getVersionTag() = decisionDefinitionBean.versionTag
  override fun getName() = decisionDefinitionBean.name
  override fun getId() = decisionDefinitionBean.id
  override fun getDeploymentId() = decisionDefinitionBean.deploymentId
  override fun getCategory() = decisionDefinitionBean.category
  override fun getKey() = decisionDefinitionBean.key
  override fun getVersion() = decisionDefinitionBean.version
  override fun getTenantId() = decisionDefinitionBean.tenantId
  override fun getResourceName() = decisionDefinitionBean.resourceName
  override fun getDiagramResourceName() = null
  override fun getHistoryTimeToLive() = decisionDefinitionBean.historyTimeToLive
  override fun getDecisionRequirementsDefinitionId() = decisionDefinitionBean.decisionRequirementsDefinitionId
  override fun getDecisionRequirementsDefinitionKey() = decisionDefinitionBean.decisionRequirementsDefinitionKey
}

/**
 * POJO to hold the values of process definition.
 */
data class DecisionDefinitionBean(
  val versionTag: String?,
  val id: String?,
  val name: String?,
  val key: String?,
  val category: String?,
  val deploymentId: String?,
  val historyTimeToLive: Int?,
  val resourceName: String?,
  val tenantId: String?,
  val version: Int,
  val decisionRequirementsDefinitionId: String?,
  val decisionRequirementsDefinitionKey: String?
) {
  /**
   * Factory methods.
   */
  companion object {
    /**
     * Factory method to create bean from REST representation.
     */
    @JvmStatic
    fun fromDto(dto: DecisionDefinitionDto) = DecisionDefinitionBean(
      versionTag = dto.versionTag,
      id = dto.id,
      name = dto.name,
      key = dto.key,
      category = dto.category,
      deploymentId = dto.deploymentId,
      historyTimeToLive = dto.historyTimeToLive,
      resourceName = dto.resource,
      tenantId = dto.tenantId,
      version = dto.version,
      decisionRequirementsDefinitionId = dto.decisionRequirementsDefinitionId,
      decisionRequirementsDefinitionKey = dto.decisionRequirementsDefinitionKey,
    )
  }
}
