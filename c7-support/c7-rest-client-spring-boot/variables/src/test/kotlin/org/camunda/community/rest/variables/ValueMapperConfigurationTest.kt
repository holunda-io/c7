package org.camunda.community.rest.variables

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.variable.Variables
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

internal class ValueMapperConfigurationTest {

  @TestConfiguration
  internal class ValueMapperTestConfiguration {

    @Bean
    fun objectMapper() = ObjectMapper()

  }

  @Nested
  @SpringBootTest(classes = [ValueMapperConfiguration::class])
  @Import(ValueMapperTestConfiguration::class)
  inner class JsonConfiguration {

    @Autowired
    lateinit var properties: CamundaRestClientVariablesProperties

    @Test
    fun should_use_json_serialization_as_default() {
      assertThat(properties.defaultSerializationFormat)
        .isEqualTo(Variables.SerializationDataFormats.JSON)
    }
  }

  @Nested
  @SpringBootTest(classes = [ValueMapperConfiguration::class])
  @Import(ValueMapperTestConfiguration::class)
  @TestPropertySource(properties = ["camunda.rest.client.variables.defaultSerializationFormat=JAVA"])
  inner class JavaConfiguration {

    @Autowired
    lateinit var properties: CamundaRestClientVariablesProperties

    @Test
    fun should_use_java_serialization_if_configured() {
      assertThat(properties.defaultSerializationFormat)
        .isEqualTo(Variables.SerializationDataFormats.JAVA)
    }
  }

}
