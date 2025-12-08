package io.holunda.camunda.bpm.correlate.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

/**
 * Retries DTO.
 */
class RetriesDto(
  @field:JsonProperty("retries")
  var retries: Int,
  @field:JsonProperty("nextRetry")
  var nextRetry: ZonedDateTime
)

