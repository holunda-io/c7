package io.holunda.camunda.rest.clock

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.camunda.bpm.engine.impl.util.ClockUtil
import java.time.Instant
import java.util.Date

/** Controls Camunda's JVM-wide clock. Auto-discovered by Jersey or registered explicitly in JAX-RS. */
@Path("/clock")
@Produces(MediaType.APPLICATION_JSON)
class ClockRestService {

  /** Returns the current engine time from Camunda's JVM-wide clock. */
  @GET
  fun getTime() = ClockTime(ClockUtil.getCurrentTime().toInstant())

  /** Freezes engine time until another update or reset. */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  fun setTime(request: SetClockTime?): ClockTime {
    val currentTime = request?.currentTime
      ?: throw BadRequestException("currentTime is required")
    val date = try {
      Date.from(currentTime)
    } catch (exception: IllegalArgumentException) {
      throw BadRequestException("currentTime is outside the supported range", exception)
    }
    ClockUtil.setCurrentTime(date)
    return getTime()
  }

  /** Restores the running system clock. */
  @DELETE
  fun resetTime() = ClockTime(ClockUtil.resetClock().toInstant())
}

/** ISO-8601 timestamp returned by the clock endpoints. */
data class ClockTime(@get:JsonFormat(shape = JsonFormat.Shape.STRING) val currentTime: Instant)

/** No-argument request bean compatible with Camunda's Jackson configuration. */
class SetClockTime {
  var currentTime: Instant? = null
}
