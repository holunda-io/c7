
package org.camunda.community.rest.impl

import org.camunda.community.rest.adapter.AbstractHistoryServiceAdapter
import org.camunda.community.rest.client.api.HistoryApiClient
import org.camunda.community.rest.impl.query.DelegatingHistoricProcessInstanceQuery
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Remote implementation of Camunda Core HistoryService API, delegating
 * all request over HTTP to a remote Camunda Engine.
 */
@Component
@Qualifier("remote")
class RemoteHistoryService(
  private val historyApiClient: HistoryApiClient
) : AbstractHistoryServiceAdapter() {

  override fun createHistoricProcessInstanceQuery() = DelegatingHistoricProcessInstanceQuery(historyApiClient)

}
