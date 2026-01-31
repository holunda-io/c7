package org.camunda.community.rest.impl.query

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.rest.client.api.BatchApi
import org.camunda.community.rest.client.api.BatchApiClient
import org.camunda.community.rest.client.api.IncidentApiClient
import org.camunda.community.rest.client.model.BatchDto
import org.camunda.community.rest.client.model.CountResultDto
import org.camunda.community.rest.client.model.IncidentDto
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq

import org.mockito.kotlin.any
import org.mockito.kotlin.isNull
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.ResponseEntity
import java.time.OffsetDateTime
import java.util.*

class DelegatingBatchQueryTest {

  val batchApiClient = mock<BatchApiClient>()

  val query = DelegatingBatchQuery(
    batchApiClient,
  ).apply {
    this.batchId("batchId")
    this.type("type")
    this.suspended()
    this.tenantIdIn("tenantId")
    this.orderById().asc()
  }

  @Test
  fun listPage() {
    whenever(
      batchApiClient.getBatches(
        any(), any(), any(), any(), any(),
        any(), any(), any(), any(), isNull(), isNull(), isNull(),
        isNull(), isNull()
      )
    )
      .thenReturn(
        ResponseEntity.ok(
          listOf(
            BatchDto().id("batchId").type("type").startTime(OffsetDateTime.now()).tenantId("tenantId")
              .batchJobDefinitionId("batchJobDefinitionId").batchJobsPerSeed(1).invocationsPerBatchJob(4).totalJobs(10).jobsCreated(10).suspended(false)
          )
        )
      )
    val result = query.listPage(1, 10)
    assertThat(result).hasSize(1)
  }

  @Test
  fun count() {
    whenever(batchApiClient.getBatchesCount(any(), any(), any(), any(), any(),
      isNull(), isNull(), isNull(), isNull(), isNull()))
      .thenReturn(
        ResponseEntity.ok(CountResultDto().count(5))
      )
    val result = query.count()
    assertThat(result).isEqualTo(5)
  }

}
