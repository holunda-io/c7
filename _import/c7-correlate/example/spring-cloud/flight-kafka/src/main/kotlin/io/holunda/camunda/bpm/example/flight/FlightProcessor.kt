package io.holunda.camunda.bpm.example.flight

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.holunda.camunda.bpm.example.common.domain.flight.BookFlightCommand
import io.holunda.camunda.bpm.example.common.domain.flight.FlightReservationConfirmedEvent
import io.holunda.camunda.bpm.example.common.domain.flight.FlightService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

/**
 * Flight processor, typical Spring Cloud Function implementation.
 */
@Component
class FlightProcessor(
  private val objectMapper: ObjectMapper,
  private val flightService: FlightService
) : java.util.function.Function<Message<ByteArray>, Message<ByteArray>> {

  override fun apply(message: Message<ByteArray>): Message<ByteArray> {

    val command = objectMapper.readValue<BookFlightCommand>(message.payload)

    logger.info { "Received command: $command" }
    val result = flightService.bookFlight(command)
    logger.info { "Sending result: $result" }
    return MessageBuilder.createMessage(
      objectMapper.writeValueAsBytes(result), MessageHeaders(
        mapOf(
          "X-CORRELATE-PayloadType-FQCN" to FlightReservationConfirmedEvent::class.java.canonicalName.toByteArray()
        )
      )
    )
  }

}
