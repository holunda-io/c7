package io.holunda.camunda.bpm.correlate.correlation

import io.holunda.camunda.bpm.correlate.persist.MessagePersistenceService
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
/**
 * Main processor driving the correlation.
 */
class BatchCorrelationProcessor(
  private val persistenceService: MessagePersistenceService,
  private val correlationService: BatchCorrelationService,
  private val correlationMetrics: CorrelationMetrics
) {

  /**
   * Runs correlation of messages stored in the inbox.
   */
  fun correlate() {
    persistenceService
      .fetchMessageBatches()
      .filterNot { it.correlationMessages.isEmpty() }
      .forEach { batch ->
        try {
          logger.debug { "Correlating batch ${batch.groupingKey} containing ${batch.correlationMessages.size} messages." }
          val result: CorrelationBatchResult = correlationService.correlateBatch(batch)
          logger.debug { "Processing result for batch ${batch.groupingKey}: $result" }
          when (result) {
            is CorrelationBatchResult.Success -> {
              persistenceService.success(successfulCorrelations = result.successfulCorrelations)
              correlationMetrics.incrementSuccess(result.successfulCorrelations.size)
            }

            is CorrelationBatchResult.Error -> {
              persistenceService.success(successfulCorrelations = result.successfulCorrelations)
              correlationMetrics.incrementSuccess(result.successfulCorrelations.size)
              persistenceService.error(errorCorrelations = result.errorCorrelations)
              correlationMetrics.incrementError(result.errorCorrelations.size)
            }
          }
        } catch (e: Exception) {
          persistenceService.error(
            mapOf(
              Pair(
                first = batch.correlationMessages.first().messageMetaData,
                second = (e.message ?: "Error without message of type ${e::class.java.name}")
              )
            )
          )
          correlationMetrics.incrementError()
          logger.trace(e) { "Error processing for batch ${batch.groupingKey}" }
        }
      }
  }
}
