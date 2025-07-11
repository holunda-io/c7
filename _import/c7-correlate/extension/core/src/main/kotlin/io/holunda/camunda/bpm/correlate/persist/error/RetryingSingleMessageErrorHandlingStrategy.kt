package io.holunda.camunda.bpm.correlate.persist.error

import io.holunda.camunda.bpm.correlate.persist.MessageEntity
import io.holunda.camunda.bpm.correlate.persist.MessageErrorHandlingResult
import io.holunda.camunda.bpm.correlate.persist.SingleMessageErrorHandlingStrategy
import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import kotlin.math.pow

private val logger = KotlinLogging.logger {}

/**
 * Single message retrying strategy focusing on retries based on timing configured in the config.
 */
class RetryingSingleMessageErrorHandlingStrategy(
  private val clock: Clock,
  private val retryErrorHandlingConfig: RetryingErrorHandlingConfig
) : SingleMessageErrorHandlingStrategy {

  override fun evaluateMessageError(entity: MessageEntity, errorDescription: String): MessageErrorHandlingResult {
    return if (isAlive(entity)) {
      // still alive, no error
      logger.trace { "Error message is still inside TTL, not reporting any error." }
      MessageErrorHandlingResult.NoOp
    } else {
      MessageErrorHandlingResult.Retry(
        entity.apply {
          val retries = this.retries + 1 // increment retry
          this.retries = retries
          this.nextRetry = calculateNextRetry(now = clock.instant(), retries = retries)
          this.error = errorDescription.substring(0, errorDescription.length.coerceAtMost(9999)) // FIXME -> determine the length
        }
      )
    }
  }

  /*
   * Calculate when to execute next retry.
   */
  private fun calculateNextRetry(now: Instant, retries: Int): Instant {
    return now.plus(
      retryErrorHandlingConfig.getBackoffExponentBase()
        .pow(retries).toLong()
        .coerceAtMost(retryErrorHandlingConfig.getMaximumBackOffSeconds()), ChronoUnit.MINUTES
    )
  }


  /*
   * Checks if the message is still alive and can be reprocessed.
   */
  private fun isAlive(entity: MessageEntity): Boolean {
    return if (entity.timeToLiveDuration != null) {
      val titleToLiveDuration = try {
        Duration.parse(entity.timeToLiveDuration)
      } catch (e: DateTimeParseException) {
        logger.trace { "Error parsing TTL of message ${entity.id}, ignored." }
        null
      }
      if (titleToLiveDuration != null) {
        entity.inserted.plus(titleToLiveDuration).isAfter(clock.instant())
      } else {
        false
      }
    } else {
      false
    }
  }
}
