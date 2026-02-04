package org.camunda.community.rest.itest.boot4

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.variable.Variables
import org.camunda.community.rest.client.api.TaskApiClient
import org.camunda.community.rest.itest.stages.TestApplication
import org.camunda.community.rest.variables.ValueMapper
import org.camunda.community.rest.variables.ValueTypeRegistration
import org.camunda.community.rest.variables.ValueTypeResolverImpl
import org.camunda.community.rest.variables.serialization.SpinJsonValueSerializer
import org.camunda.spin.plugin.variable.value.SpinValue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.FilteredClassLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles

internal class SpringBootConfigurationITest {

  @Configuration
  class CustomValueMapperConfiguration {
    companion object {
      val VALUE_MAPPER: ValueMapper =
          ValueMapper(
              objectMapper = jacksonObjectMapper(), valueTypeResolver = ValueTypeResolverImpl(),
              valueTypeRegistration = ValueTypeRegistration(),
              customValueSerializers = emptyList(),
              valueSerializers = emptyList(),
              serializationFormat = Variables.SerializationDataFormats.JSON
          )
    }

    @Bean
    fun myValueMapper(): ValueMapper {
      return VALUE_MAPPER
    }
  }


  @Nested
  @SpringBootTest(
    classes = [TestApplication::class],
  )
  @ActiveProfiles("itest")
  inner class ExtensionEnabledByDefaultTest {

    @Autowired(required = false)
    @Qualifier("remote")
    lateinit var runtimeService: RuntimeService

    @Autowired(required = false)
    lateinit var valueMapper: ValueMapper

    @Autowired(required = false)
    lateinit var spinValueSerializer: SpinJsonValueSerializer

    @Autowired(required = false)
    lateinit var taskApiClient: TaskApiClient

    @Test
    fun `components are initialized`() {
      Assertions.assertThat(this::runtimeService.isInitialized).isTrue()
      Assertions.assertThat(this::taskApiClient.isInitialized).isTrue()
      Assertions.assertThat(this::valueMapper.isInitialized).isTrue()
      Assertions.assertThat(this::spinValueSerializer.isInitialized).isTrue()
      Assertions.assertThat(this.valueMapper).isNotEqualTo(CustomValueMapperConfiguration.Companion.VALUE_MAPPER)
    }
  }

  @Nested
  @SpringBootTest(
    classes = [CustomValueMapperConfiguration::class, TestApplication::class],
  )
  @ActiveProfiles("itest")
  inner class ExtensionEnabledWithOverriddenValueMapperTest {

    @Autowired(required = false)
    @Qualifier("remote")
    lateinit var runtimeService: RuntimeService

    @Autowired(required = false)
    lateinit var valueMapper: ValueMapper

    @Autowired(required = false)
    lateinit var taskApiClient: TaskApiClient

    @Test
    fun `components are initialized using overridden valueMapper`() {
      Assertions.assertThat(this::runtimeService.isInitialized).isTrue()
      Assertions.assertThat(this::valueMapper.isInitialized).isTrue()
      Assertions.assertThat(this::taskApiClient.isInitialized).isTrue()
      Assertions.assertThat(this.valueMapper).isEqualTo(CustomValueMapperConfiguration.Companion.VALUE_MAPPER)
    }
  }

  @Nested
  @SpringBootTest(
    classes = [TestApplication::class],
    properties = ["camunda.rest.client.enabled=false"]
  )
  @ActiveProfiles("itest")
  inner class ExtensionDisabledPerPropertyTest {

    @Autowired(required = false)
    @Qualifier("remote")
    lateinit var runtimeService: RuntimeService

    @Autowired(required = false)
    lateinit var valueMapper: ValueMapper

    @Autowired(required = false)
    lateinit var taskApiClient: TaskApiClient

    @Test
    fun `no components are initialized`() {
      Assertions.assertThat(this::runtimeService.isInitialized).isFalse()
      Assertions.assertThat(this::valueMapper.isInitialized).isFalse()
      Assertions.assertThat(this::taskApiClient.isInitialized).isFalse()
    }

  }

  @Nested
  inner class ExtensionEnabledByDefaultNoSpinTest {

    @Test
    fun `components are initialized without spin`() {
      ApplicationContextRunner()
        .withInitializer { it.environment.setActiveProfiles("itest") }
        .withPropertyValues("camunda.bpm.generic-properties.properties.historyTimeToLive=P1D")
        .withUserConfiguration(TestApplication::class.java)
        .withClassLoader(FilteredClassLoader(SpinValue::class.java))
        .run {
          Assertions.assertThatThrownBy { it.getBean(SpinJsonValueSerializer::class.java) }
              .isInstanceOf(NoSuchBeanDefinitionException::class.java)
          Assertions.assertThat(it.getBean(ValueMapper::class.java)).isNotNull
        }

    }
  }

}
