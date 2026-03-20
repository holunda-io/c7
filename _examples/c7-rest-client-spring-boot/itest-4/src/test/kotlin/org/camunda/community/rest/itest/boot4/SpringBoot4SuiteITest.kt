package org.camunda.community.rest.itest.boot4

import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.platform.suite.api.BeforeSuite
import org.junit.platform.suite.api.ExcludePackages
import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.GenericContainer

val logger = KotlinLogging.logger {}

@Suite
@SuiteDisplayName("SpringBoot 4.x Integration Tests")
@SelectPackages("org.camunda.community.rest.itest")
@ExcludePackages("org.camunda.community.rest.itest.boot4")
class SpringBoot4SuiteITest {

  companion object {

    private val camundaContainer = GenericContainer("camunda/camunda-bpm-platform:run-latest")
      .withEnv("JAVA_OPTS", "-Dcamunda.bpm.generic-properties.properties.javaSerializationFormatEnabled=true")
      .withExposedPorts(8080)

    @BeforeSuite
    @JvmStatic
    fun initContainer() {
      camundaContainer.start()
    }

  }

  class CamundaPropertiesInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
      if (camundaContainer.isRunning) {
        logger.info { "Setting Camunda REST URL to ${camundaContainer.host}:${camundaContainer.firstMappedPort}" }
        TestPropertyValues.of(
          mapOf(Pair("feign.client.config.default.url", "http://${camundaContainer.host}:${camundaContainer.firstMappedPort}/engine-rest/"))
        ).applyTo(applicationContext)
      }
    }
  }

}
