

package org.camunda.community.rest.variables

import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.variable.type.ValueTypeResolver
import org.camunda.community.rest.variables.serialization.CustomValueSerializer
import org.camunda.community.rest.variables.serialization.JavaSerializationValueSerializer
import org.camunda.community.rest.variables.serialization.JsonValueSerializer
import org.camunda.community.rest.variables.serialization.SpinJsonValueSerializer
import org.camunda.community.rest.variables.serialization.SpinXmlValueSerializer
import org.camunda.spin.plugin.variable.value.SpinValue
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@AutoConfiguration
@EnableConfigurationProperties(value = [CamundaRestClientVariablesProperties::class])
class ValueMapperConfiguration {

  @Bean
  @ConditionalOnMissingBean(ValueTypeResolver::class)
  fun defaultValueTypeResolver(): ValueTypeResolver {
    return ValueTypeResolverImpl()
  }

  @Bean
  fun valueTypeRegistration() = ValueTypeRegistration()

  @Bean
  @ConditionalOnMissingBean(SpinXmlValueSerializer::class)
  @ConditionalOnClass(SpinValue::class)
  fun spinXmlValueSerializer(valueTypeResolver: ValueTypeResolver, valueTypeRegistration: ValueTypeRegistration): SpinXmlValueSerializer {
    return SpinXmlValueSerializer(valueTypeResolver = valueTypeResolver, valueTypeRegistration = valueTypeRegistration)
  }

  @Bean
  @ConditionalOnMissingBean(SpinJsonValueSerializer::class)
  @ConditionalOnClass(SpinValue::class)
  fun spinJsonValueSerializer(valueTypeResolver: ValueTypeResolver, valueTypeRegistration: ValueTypeRegistration): SpinJsonValueSerializer {
    return SpinJsonValueSerializer(valueTypeResolver = valueTypeResolver, valueTypeRegistration = valueTypeRegistration)
  }

  @Bean
  @ConditionalOnMissingBean(ValueMapper::class)
  fun defaultValueMapper(
    objectMapper: ObjectMapper,
    valueTypeResolver: ValueTypeResolver,
    valueTypeRegistration: ValueTypeRegistration,
    customValueSerializers: List<CustomValueSerializer>,
    customValueMappers: List<CustomValueMapper>,
    properties: CamundaRestClientVariablesProperties
  ): ValueMapper {
    val wrapperCustomValueMappers = customValueMappers.map {
      CustomValueMapperAdapter(it)
    }
    return ValueMapper(
      objectMapper = objectMapper,
      valueTypeResolver = valueTypeResolver,
      valueTypeRegistration = valueTypeRegistration,
      valueSerializers = listOf(JavaSerializationValueSerializer(), JsonValueSerializer(objectMapper)),
      customValueSerializers = customValueSerializers + wrapperCustomValueMappers,
      serializationFormat = properties.defaultSerializationFormat
    )
  }
}
