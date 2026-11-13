package io.holunda.camunda.testing.rest

import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.impl.util.ClockUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.FilteredClassLoader
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.Instant
import java.util.Date

@ResourceLock("camunda-clock")
class CamundaTestRestApiTest {

  private val runner = WebApplicationContextRunner().withConfiguration(
    AutoConfigurations.of(
      CamundaTestRestAutoConfiguration::class.java,
      JacksonAutoConfiguration::class.java,
      WebMvcAutoConfiguration::class.java
    )
  )

  @AfterEach
  fun resetClock() {
    ClockUtil.reset()
  }

  @Test
  fun `endpoints are absent unless explicitly enabled`() {
    for (properties in listOf(emptyArray(), arrayOf("camunda.bpm.test-rest.enabled=false"))) {
      runner.withPropertyValues(*properties).run { context ->
        assertThat(context).doesNotHaveBean(CamundaClockController::class.java)
        MockMvcBuilders.webAppContextSetup(context).build()
          .perform(get("/camunda-test/clock"))
          .andExpect(status().isNotFound)
      }
    }
  }

  @Test
  fun `does not activate outside servlet applications`() {
    ApplicationContextRunner()
      .withConfiguration(AutoConfigurations.of(CamundaTestRestAutoConfiguration::class.java))
      .withPropertyValues("camunda.bpm.test-rest.enabled=true")
      .run { context -> assertThat(context).doesNotHaveBean(CamundaClockController::class.java) }
  }

  @Test
  fun `does not activate without Camunda`() {
    runner.withClassLoader(FilteredClassLoader("org.camunda.bpm.engine"))
      .withPropertyValues("camunda.bpm.test-rest.enabled=true")
      .run { context -> assertThat(context).doesNotHaveBean(CamundaClockController::class.java) }
  }

  @Test
  fun `reads freezes and resets the engine clock over HTTP`() {
    runner.withPropertyValues("camunda.bpm.test-rest.enabled=true").run { context ->
      val mvc = MockMvcBuilders.webAppContextSetup(context).build()
      val frozenTime = Instant.parse("2030-05-01T12:30:00Z")
      mvc.perform(put("/camunda-test/clock")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""{"currentTime":"2030-05-01T14:30:00+02:00"}"""))
        .andExpect(status().isOk)
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.currentTime").value(frozenTime.toString()))
      assertThat(ClockUtil.getCurrentTime()).isEqualTo(Date.from(frozenTime))
      mvc.perform(get("/camunda-test/clock"))
        .andExpect(status().isOk)
        .andExpect(jsonPath("$.currentTime").value(frozenTime.toString()))

      val beforeReset = System.currentTimeMillis()
      mvc.perform(delete("/camunda-test/clock")).andExpect(status().isOk)
      assertThat(ClockUtil.getCurrentTime().time).isBetween(beforeReset, System.currentTimeMillis())
    }
  }

  @Test
  fun `invalid requests return 400 and leave the clock unchanged`() {
    runner.withPropertyValues("camunda.bpm.test-rest.enabled=true").run { context ->
      val mvc = MockMvcBuilders.webAppContextSetup(context).build()
      val frozenTime = Date.from(Instant.parse("2030-05-01T12:30:00Z"))
      ClockUtil.setCurrentTime(frozenTime)
      for (body in listOf("{}", """{"currentTime":null}""", """{"currentTime":"invalid"}""",
        """{"currentTime":"2030-05-01T12:30:00"}""",
        """{"currentTime":"+1000000000-12-31T23:59:59.999999999Z"}""", "{", "")) {
        mvc.perform(put("/camunda-test/clock").contentType(MediaType.APPLICATION_JSON).content(body))
          .andExpect(status().isBadRequest)
        assertThat(ClockUtil.getCurrentTime()).isEqualTo(frozenTime)
      }
    }
  }
}
