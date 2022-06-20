package io.holunda.deployment

import io.holunda.deployment.CamundaDeploymentProperties.ProcessArchive
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CamundaDeploymentPropertiesTest {

  @Test
  internal fun `name must be unique`() {
    Assertions.assertThatThrownBy {
      CamundaDeploymentProperties(
        listOf(
          ProcessArchive("one", "one", "/one"),
          ProcessArchive("one", "two", "/two")
        )
      )
    }.isInstanceOf(ProcessArchiveNameNotUniqueException::class.java)
  }

  @Test
  internal fun `tenant must be unique`() {
    Assertions.assertThatThrownBy {
      CamundaDeploymentProperties(
        listOf(
          ProcessArchive("one", "one", "/one"),
          ProcessArchive("two", "one", "/two")
        )
      )
    }.isInstanceOf(ProcessArchiveTenantNotUniqueException::class.java)
  }
}
