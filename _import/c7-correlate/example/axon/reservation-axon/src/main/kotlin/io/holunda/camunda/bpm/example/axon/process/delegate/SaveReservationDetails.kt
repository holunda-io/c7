package io.holunda.camunda.bpm.example.axon.process.delegate

import io.holunda.camunda.bpm.data.CamundaBpmData.reader
import io.holunda.camunda.bpm.example.axon.ReservationProcessing.Variables.CUSTOMER_NAME
import io.holunda.camunda.bpm.example.axon.ReservationProcessing.Variables.DELAY
import io.holunda.camunda.bpm.example.axon.ReservationProcessing.Variables.RESERVATION_ID
import io.github.oshai.kotlinlogging.KotlinLogging
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
/**
 * Component saving the reservation details.
 * Takes a long time to finish.
 */
@Component
class SaveReservationDetails : JavaDelegate {

  override fun execute(execution: DelegateExecution) {
    val reader = reader(execution)
    logger.info { "[SAVING-DELEGATE]: Saving reservation (${reader.get(RESERVATION_ID)}) details for customer ${reader.get(CUSTOMER_NAME)}." }
    busyWait(reader.get(DELAY))
    logger.info { "[SAVING-DELEGATE]: Reservation (${reader.get(RESERVATION_ID)}) details saved." }
  }

  private fun busyWait(delay: Long) {
    for (i in 0..delay) {
      logger.debug { "[SAVING-DELEGATE]: Progress ${i}/${delay}." }
      Thread.sleep(1000)
    }
  }
}
