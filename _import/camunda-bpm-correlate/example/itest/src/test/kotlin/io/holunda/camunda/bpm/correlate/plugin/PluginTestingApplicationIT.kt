package io.holunda.camunda.bpm.correlate.plugin

import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.WaitUntilState
import io.holunda.camunda.bpm.correlate.correlation.BatchCorrelationSchedulerConfiguration
import io.holunda.camunda.bpm.correlate.correlation.SingleMessageCorrelationStrategy
import io.holunda.camunda.bpm.correlate.persist.MessageEntity
import io.holunda.camunda.bpm.correlate.persist.MessageRepository
import io.holunda.camunda.bpm.correlate.persist.impl.InMemMessageRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import java.nio.file.Paths
import java.time.Instant
import java.util.*


@SpringBootTest(
  classes = [PluginTestingApplicationIT.TestApplication::class],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@ActiveProfiles("cockpit")
class PluginTestingApplicationIT {

  @Autowired
  private lateinit var messageRepository: MessageRepository

  @LocalServerPort
  private var serverPort: Int = 0

  private var messageId: String = UUID.randomUUID().toString()

  @BeforeEach
  fun `ingest messages`() {
    messageRepository.save(
      MessageEntity(
        id = messageId,
        payloadEncoding = "JSON",
        payloadTypeNamespace = "io.holunda.example",
        payloadTypeName = "MyMessage",
        payloadTypeRevision = null,
        payload = "{ 'key': 'value' }".toByteArray(Charsets.UTF_8),
        inserted = Instant.now(),
        timeToLiveDuration = null,
        retries = 2,
        nextRetry = Instant.now().plusMillis(20_000),
        error = "Some error happened",
        expiration = null
      )
    )
  }

  @Test
  fun `should access message by id inside the cockpit`() {
    Playwright.create().use { playwright ->
      val browser = playwright.chromium().launch()
      val page: Page = browser.newPage()
      page.navigate(
        "http://localhost:${serverPort}/camunda/app/cockpit/default/#/correlation",
        Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE)
      )
      page.screenshot(
        Page.ScreenshotOptions()
          .setPath(Paths.get("target/cockpit-test-screenshot.png"))
      )
      assertThat(page.getByText(messageId)).hasCount(1)
    }
  }

  @SpringBootApplication(exclude = [BatchCorrelationSchedulerConfiguration::class])
  class TestApplication {

    @Bean
    fun inMemoryRepository() = InMemMessageRepository()

    @Bean
    fun singleMessageCorrelationStrategy(): SingleMessageCorrelationStrategy = mock()
  }

}
