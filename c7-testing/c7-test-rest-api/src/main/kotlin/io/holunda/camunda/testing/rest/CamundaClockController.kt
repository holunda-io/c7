package io.holunda.camunda.testing.rest

import com.fasterxml.jackson.annotation.JsonFormat
import org.camunda.bpm.engine.impl.util.ClockUtil
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.Date

/** Test-only access to Camunda's JVM-wide clock. */
@RestController
@RequestMapping("/camunda-test/clock")
@ConditionalOnProperty(
  prefix = "camunda.bpm.test-rest", name = ["enabled"], havingValue = "true"
)
class CamundaClockController {

  @GetMapping
  fun getTime() = ClockTime(ClockUtil.getCurrentTime().toInstant())

  /** Freezes engine time until another update or reset. */
  @PutMapping
  fun setTime(@RequestBody request: SetClockTime): ClockTime {
    val currentTime = request.currentTime
      ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "currentTime is required")
    val date = try {
      Date.from(currentTime)
    } catch (exception: IllegalArgumentException) {
      throw ResponseStatusException(HttpStatus.BAD_REQUEST, "currentTime is outside the supported range", exception)
    }
    ClockUtil.setCurrentTime(date)
    return getTime()
  }

  /** Restores the running system clock. */
  @DeleteMapping
  fun resetTime() = ClockTime(ClockUtil.resetClock().toInstant())
}

/** ISO-8601 timestamp returned by the clock endpoints. */
data class ClockTime(@get:JsonFormat(shape = JsonFormat.Shape.STRING) val currentTime: Instant)

/** A no-argument request bean also works without Jackson's optional Kotlin module. */
class SetClockTime {
  var currentTime: Instant? = null
}
