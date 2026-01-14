package org.camunda.community.rest.itest.stages

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat
import org.camunda.spin.spi.DataFormatConfigurator
import java.text.SimpleDateFormat


/**
 * Configured SPIN Jackson Mapper.
 * Don't forget to look into **META-INF/services**
 */
class JacksonDataFormatConfigurator : DataFormatConfigurator<JacksonJsonDataFormat> {

  companion object {
    fun configureObjectMapper(objectMapper: ObjectMapper) = objectMapper.apply {
      registerModule(KotlinModule.Builder().build())
      registerModule(Jdk8Module())
      registerModule(JavaTimeModule())
      dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:MM:ss.SSSz")
    }
  }

  override fun configure(dataFormat: JacksonJsonDataFormat) {
    configureObjectMapper(dataFormat.objectMapper)
  }

  override fun getDataFormatClass(): Class<JacksonJsonDataFormat> {
    return JacksonJsonDataFormat::class.java
  }
}
