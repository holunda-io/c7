package org.camunda.community.rest.impl.query

import org.camunda.bpm.engine.ProcessEngineException
import org.camunda.bpm.engine.repository.DecisionDefinition
import org.camunda.bpm.engine.repository.DecisionDefinitionQuery
import org.camunda.community.rest.adapter.DecisionDefinitionAdapter
import org.camunda.community.rest.adapter.DecisionDefinitionBean
import org.camunda.community.rest.client.api.DecisionDefinitionApiClient
import org.springframework.web.bind.annotation.RequestParam
import java.util.*
import kotlin.reflect.KParameter

/**
 * Implementation of the decision definition query.
 */
class DelegatingDecisionDefinitionQuery(
  private val decisionDefinitionApiClient: DecisionDefinitionApiClient,
  var id: String? = null,
  var ids: Array<out String>? = null,
  var category: String? = null,
  var categoryLike: String? = null,
  var name: String? = null,
  var nameLike: String? = null,
  var deploymentId: String? = null,
  var deployedAfter: Date? = null,
  var deployedAt: Date? = null,
  var key: String? = null,
  var keys: Array<out String>? = null,
  var keyLike: String? = null,
  var resourceName: String? = null,
  var resourceNameLike: String? = null,
  var decisionRequirementsDefinitionId: String? = null,
  var decisionRequirementsDefinitionKey: String? = null,
  var withoutDecisionRequirementsDefinition: Boolean = false,
  var version: Int? = null,
  var latest: Boolean = false,
  var includeDefinitionsWithoutTenantId: Boolean = false,
  var versionTag: String? = null,
  var versionTagLike: String? = null,
) : BaseQuery<DecisionDefinitionQuery, DecisionDefinition>(), DecisionDefinitionQuery {

  override fun decisionDefinitionId(decisionDefinitionId: String?) = this.apply { this.id = requireNotNull(decisionDefinitionId) }

  override fun decisionDefinitionIdIn(vararg decisionDefinitionIdIn: String) = this.apply { this.ids = decisionDefinitionIdIn }

  override fun decisionDefinitionCategory(decisionDefinitionCategory: String?) = this.apply { this.category = requireNotNull(decisionDefinitionCategory) }

  override fun decisionDefinitionCategoryLike(decisionDefinitionCategoryLike: String?) = this.apply { this.categoryLike = requireNotNull(decisionDefinitionCategoryLike) }

  override fun decisionDefinitionName(decisionDefinitionName: String?) = this.apply { this.name = requireNotNull(decisionDefinitionName) }

  override fun decisionDefinitionNameLike(decisionDefinitionNameLike: String?) = this.apply { this.nameLike = requireNotNull(decisionDefinitionNameLike) }

  override fun decisionRequirementsDefinitionId(decisionRequirementsDefinitionId: String?) = this.apply { this.decisionRequirementsDefinitionId = requireNotNull(decisionRequirementsDefinitionId) }

  override fun decisionRequirementsDefinitionKey(decisionRequirementsDefinitionKey: String?) = this.apply { this.decisionRequirementsDefinitionKey = requireNotNull(decisionRequirementsDefinitionKey) }

  override fun withoutDecisionRequirementsDefinition() = this.apply { this.withoutDecisionRequirementsDefinition = true }

  override fun includeDecisionDefinitionsWithoutTenantId() = this.apply { this.includeDefinitionsWithoutTenantId = true }

  override fun deploymentId(deploymentId: String?) = this.apply { this.deploymentId = requireNotNull(deploymentId) }

  override fun deployedAfter(deployedAfter: Date?) = this.apply { this.deployedAfter = requireNotNull(deployedAfter) }

  override fun deployedAt(deployedAt: Date?) = this.apply { this.deployedAt = requireNotNull(deployedAt) }

  override fun decisionDefinitionKey(decisionDefinitionKey: String?) = this.apply { this.key = requireNotNull(decisionDefinitionKey) }

  override fun decisionDefinitionKeyLike(decisionDefinitionKeyLike: String?) = this.apply { this.keyLike = requireNotNull(decisionDefinitionKeyLike) }

  override fun decisionDefinitionVersion(decisionDefinitionVersion: Int?) = this.apply { this.version = requireNotNull(decisionDefinitionVersion) }

  override fun latestVersion() = this.apply { this.latest = true }

  override fun decisionDefinitionResourceName(decisionDefinitionResourceName: String?) = this.apply { this.resourceName = requireNotNull(decisionDefinitionResourceName) }

  override fun decisionDefinitionResourceNameLike(decisionDefinitionResourceNameLike: String?) = this.apply { this.resourceNameLike = requireNotNull(decisionDefinitionResourceNameLike) }

  override fun versionTag(versionTag: String?) = this.apply {
    this.versionTag = requireNotNull(versionTag)
  }

  override fun versionTagLike(versionTagLike: String?) = this.apply { this.versionTagLike = requireNotNull(versionTagLike) }

  override fun orderByDeploymentId() = this.apply { orderBy("deploymentId") }

  override fun orderByDeploymentTime() = this.apply { orderBy("deploymentTime") }

  override fun orderByVersionTag() = this.apply { orderBy("versionTag") }

  override fun orderByDecisionDefinitionCategory() = this.apply { orderBy("category") }

  override fun orderByDecisionDefinitionId() = this.apply { orderBy("id") }

  override fun orderByDecisionDefinitionKey() = this.apply { orderBy("key") }

  override fun orderByDecisionDefinitionName() = this.apply { orderBy("name") }

  override fun orderByDecisionDefinitionVersion() = this.apply { orderBy("version") }

  override fun orderByDecisionRequirementsDefinitionKey() = this.apply { orderBy("decisionRequirementsDefinitionKey") }

  override fun listPage(firstResult: Int, maxResults: Int): List<DecisionDefinition> {
    validate()
    with(DecisionDefinitionApiClient::getDecisionDefinitions) {
      val result = callBy(parameters.associateWith { parameter ->
        when (parameter.kind) {
          KParameter.Kind.INSTANCE -> decisionDefinitionApiClient
          else -> {
            when (parameter.annotations.find { it is RequestParam }?.let { (it as RequestParam).value }) {
              "firstResult" -> firstResult
              "maxResults" -> maxResults
              else -> this@DelegatingDecisionDefinitionQuery.getQueryParam(parameter)
            }
          }
        }
      })
      return result.body!!.map {
        DecisionDefinitionAdapter(DecisionDefinitionBean.fromDto(it))
      }
    }
  }

  override fun count(): Long {
    validate()
    with (DecisionDefinitionApiClient::getDecisionDefinitionsCount) {
      val result = callBy(parameters.associateWith { parameter ->
        when (parameter.kind) {
          KParameter.Kind.INSTANCE -> decisionDefinitionApiClient
          else -> this@DelegatingDecisionDefinitionQuery.getQueryParam(parameter)
        }
      })
      return result.body!!.count!!
    }
  }

  override fun validate() {
    super.validate()
    if (latest && (id != null || version != null || deploymentId != null)) {
      throw ProcessEngineException("Calling latest() can only be used in combination with key(String) and keyLike(String) or name(String) and nameLike(String)")
    }
  }

  private fun getQueryParam(parameter: KParameter): Any? {
    val value = parameter.annotations.find { it is RequestParam }?.let { (it as RequestParam).value }
    return when(value) {
      "decisionDefinitionId" -> this.id
      "decisionDefinitionIdIn" -> this.ids?.joinToString(",")
      "keysIn" -> this.keys?.joinToString(",")
      "latestVersion" -> this.latest
      "tenantIdIn" -> this.tenantIds?.joinToString(",")
      "withoutTenantId" -> this.tenantIdsSet && (this.tenantIds == null)
      "includeDecisionDefinitionsWithoutTenantId" -> includeDefinitionsWithoutTenantId
      "withoutDecisionRequirementsDefinition" -> withoutDecisionRequirementsDefinition
      "sortBy" -> sortProperty()?.property
      "sortOrder" -> sortProperty()?.direction?.let { if (it == SortDirection.DESC) "desc" else "asc" }
      null -> throw IllegalArgumentException("value of RequestParam annotation is null")
      else -> valueForProperty(value, this, parameter.type)
    }
  }

}
