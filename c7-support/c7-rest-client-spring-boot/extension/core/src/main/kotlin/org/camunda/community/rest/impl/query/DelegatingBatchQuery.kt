package org.camunda.community.rest.impl.query

import org.camunda.bpm.engine.batch.Batch
import org.camunda.bpm.engine.batch.BatchQuery
import org.camunda.community.rest.adapter.BatchAdapter
import org.camunda.community.rest.adapter.BatchBean
import org.camunda.community.rest.client.api.BatchApiClient
import org.springframework.web.bind.annotation.RequestParam
import kotlin.reflect.KParameter

class DelegatingBatchQuery(
  private val batchApiClient: BatchApiClient,
  var batchId: String? = null,
  var type: String? = null,
  var suspensionState: SuspensionState? = null,
) : BaseQuery<BatchQuery, Batch>(), BatchQuery {

  override fun batchId(batchId: String?) = this.apply { this.batchId = requireNotNull(batchId) }

  override fun type(type: String?) = this.apply { this.type = requireNotNull(type) }

  override fun suspended() = this.apply { this.suspensionState = SuspensionState.SUSPENDED }

  override fun active() = this.apply { this.suspensionState = SuspensionState.ACTIVE }

  override fun orderById() = this.apply { orderBy("id") }

  override fun count(): Long {
    validate()
    with (BatchApiClient::getBatchesCount) {
      val result = callBy(parameters.associateWith { parameter ->
        when (parameter.kind) {
          KParameter.Kind.INSTANCE -> batchApiClient
          else -> this@DelegatingBatchQuery.getQueryParam(parameter)
        }
      })
      return result.body!!.count
    }
  }

  override fun listPage(firstResult: Int, maxResults: Int): List<Batch> {
    validate()
    with(BatchApiClient::getBatches) {
      val result = callBy(parameters.associateWith { parameter ->
        when (parameter.kind) {
          KParameter.Kind.INSTANCE -> batchApiClient
          else -> {
            when (parameter.annotations.find { it is RequestParam }?.let { (it as RequestParam).value }) {
              "firstResult" -> firstResult
              "maxResults" -> maxResults
              else -> this@DelegatingBatchQuery.getQueryParam(parameter)
            }
          }
        }
      })
      return result.body!!.map {
        BatchAdapter(BatchBean.fromDto(it))
      }
    }
  }

  private fun getQueryParam(parameter: KParameter): Any? {
    val value = parameter.annotations.find { it is RequestParam }?.let { (it as RequestParam).value }
    return when(value) {
      "id" -> batchId
      "tenantIdIn" -> tenantIds?.joinToString(",")
      "withoutTenantId" -> tenantIdsSet && tenantIds == null
      "suspended" -> suspensionState?.let { it == SuspensionState.SUSPENDED }
      "sortBy" -> sortProperty()?.property
      "sortOrder" -> sortProperty()?.direction?.let { if (it == SortDirection.DESC) "desc" else "asc" }
      "createdBy", "startedBefore", "startedAfter", "withFailures", "withoutFailures" -> null
      null -> throw IllegalArgumentException("value of RequestParam annotation is null")
      else -> valueForProperty(value, this, parameter.type)
    }
  }

}
