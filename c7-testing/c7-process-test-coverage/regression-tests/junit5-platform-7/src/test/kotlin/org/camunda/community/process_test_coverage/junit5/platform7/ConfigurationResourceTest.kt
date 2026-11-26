package org.camunda.community.process_test_coverage.junit5.platform7

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.FileNotFoundException
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path

class ConfigurationResourceTest {

  @Test
  fun should_create_an_in_memory_engine_without_configuration_resource() {
    assertThat(javaClass.classLoader.getResource("camunda.cfg.xml")).isNull()

    val extension = ProcessEngineCoverageExtension.builder().build()
    try {
      assertThat(extension.processEngineConfiguration.jdbcUrl).startsWith("jdbc:h2:mem:process-test-coverage-")
      assertThat(extension.processEngineConfiguration.historyTimeToLive).isEqualTo("P1D")
      assertThat(extension.processEngineConfiguration.isJobExecutorActivate).isFalse()
      val otherExtension = ProcessEngineCoverageExtension.builder().build()
      try {
        assertThat(otherExtension.processEngineConfiguration.jdbcUrl).isNotEqualTo(extension.processEngineConfiguration.jdbcUrl)
      } finally {
        otherExtension.processEngine.close()
      }
    } finally {
      extension.processEngine.close()
    }
  }

  @Test
  fun should_use_an_explicit_configuration_resource() {
    val extension = ProcessEngineCoverageExtension.builder("custom-camunda.cfg.xml").build()
    try {
      assertThat(extension.processEngineConfiguration.jdbcUrl).isEqualTo("jdbc:h2:mem:custom-coverage")
      assertThat(extension.processEngineConfiguration.historyTimeToLive).isEqualTo("P2D")
    } finally {
      extension.processEngine.close()
    }
  }

  @Test
  fun should_use_the_default_configuration_resource_when_present(@TempDir directory: Path) {
    javaClass.classLoader.getResourceAsStream("custom-camunda.cfg.xml")!!.use {
      Files.copy(it, directory.resolve("camunda.cfg.xml"))
    }
    val originalClassLoader = Thread.currentThread().contextClassLoader
    URLClassLoader(arrayOf(directory.toUri().toURL()), originalClassLoader).use { classLoader ->
      try {
        Thread.currentThread().contextClassLoader = classLoader
        val extension = ProcessEngineCoverageExtension.builder().build()
        try {
          assertThat(extension.processEngineConfiguration.jdbcUrl).isEqualTo("jdbc:h2:mem:custom-coverage")
          assertThat(extension.processEngineConfiguration.historyTimeToLive).isEqualTo("P2D")
        } finally {
          extension.processEngine.close()
        }
      } finally {
        Thread.currentThread().contextClassLoader = originalClassLoader
      }
    }
  }

  @Test
  fun should_fail_if_an_explicit_configuration_resource_is_missing() {
    assertThatThrownBy { ProcessEngineCoverageExtension.builder("missing-camunda.cfg.xml").build() }
      .hasRootCauseInstanceOf(FileNotFoundException::class.java)
      .hasMessageContaining("missing-camunda.cfg.xml")
  }
}
