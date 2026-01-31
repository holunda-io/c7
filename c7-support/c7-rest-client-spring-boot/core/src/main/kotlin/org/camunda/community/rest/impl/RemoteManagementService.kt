package org.camunda.community.rest.impl

import org.camunda.bpm.engine.batch.BatchQuery
import org.camunda.community.rest.adapter.AbstractManagementServiceAdapter
import org.camunda.community.rest.client.api.BatchApiClient
import org.camunda.community.rest.impl.query.DelegatingBatchQuery
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Remote implementation of Camunda Core ManagementService API, delegating
 * all request over HTTP to a remote Camunda Engine.
 */
@Component
@Qualifier("remote")
class RemoteManagementService(
  private val batchApiClient: BatchApiClient,
) : AbstractManagementServiceAdapter() {

  override fun createBatchQuery(): BatchQuery {
    return DelegatingBatchQuery(batchApiClient)
  }

}
