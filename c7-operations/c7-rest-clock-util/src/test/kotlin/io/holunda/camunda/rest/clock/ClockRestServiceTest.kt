package io.holunda.camunda.rest.clock

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.Application
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.impl.util.ClockUtil
import org.camunda.bpm.engine.rest.impl.CamundaRestResources
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory
import org.glassfish.jersey.test.spi.TestContainerFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import java.time.Instant
import java.util.Date

@ResourceLock("camunda-clock")
class ClockRestServiceTest {

  private val server = object : JerseyTest() {
    override fun configure(): Application = ResourceConfig()
      .registerClasses(CamundaRestResources.getConfigurationClasses())
      .register(ClockRestService::class.java)

    override fun getTestContainerFactory(): TestContainerFactory = InMemoryTestContainerFactory()
  }

  @BeforeEach
  fun startServer() {
    server.setUp()
  }

  @AfterEach
  fun stopServer() {
    try {
      server.tearDown()
    } finally {
      ClockUtil.reset()
    }
  }

  @Test
  fun `reads freezes and resets the clock using Camunda JSON providers without Spring`() {
    val frozenTime = Instant.parse("2030-05-01T12:30:00Z")
    server.target("clock").request().put(Entity.json("""{"currentTime":"2030-05-01T14:30:00+02:00"}""")).use {
      assertThat(it.status).isEqualTo(200)
      assertThat(it.mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE)).isTrue()
      assertThat(readTime(it)).isEqualTo(frozenTime)
    }
    assertThat(ClockUtil.getCurrentTime()).isEqualTo(Date.from(frozenTime))
    server.target("clock").request().get().use {
      assertThat(it.status).isEqualTo(200)
      assertThat(readTime(it)).isEqualTo(frozenTime)
    }
    val beforeReset = Instant.ofEpochMilli(System.currentTimeMillis())
    server.target("clock").request().delete().use {
      assertThat(it.status).isEqualTo(200)
      assertThat(readTime(it)).isBetween(beforeReset, Instant.now())
    }
    assertThat(ClockUtil.getCurrentTime().time).isBetween(beforeReset.toEpochMilli(), System.currentTimeMillis())
  }

  @Test
  fun `invalid input returns 400 without changing the clock`() {
    val frozenTime = Date.from(Instant.parse("2030-05-01T12:30:00Z"))
    ClockUtil.setCurrentTime(frozenTime)
    val invalidBodies = listOf(
      "{}", "null", """{"currentTime":null}""", """{"currentTime":"invalid"}""",
      """{"currentTime":"2030-05-01T12:30:00"}""",
      """{"currentTime":"+1000000000-12-31T23:59:59.999999999Z"}""", "{", ""
    )
    for (body in invalidBodies) {
      server.target("clock").request().put(Entity.json(body)).use {
        assertThat(it.status).describedAs("body: %s", body).isEqualTo(400)
      }
      assertThat(ClockUtil.getCurrentTime()).isEqualTo(frozenTime)
    }
  }

  @Test
  fun `requires JSON for updates`() {
    server.target("clock").request().put(Entity.text("2030-05-01T12:30:00Z")).use {
      assertThat(it.status).isEqualTo(415)
    }
  }

  private fun readTime(response: Response): Instant =
    Instant.parse(ObjectMapper().readTree(response.readEntity(String::class.java))["currentTime"].asText())
}
