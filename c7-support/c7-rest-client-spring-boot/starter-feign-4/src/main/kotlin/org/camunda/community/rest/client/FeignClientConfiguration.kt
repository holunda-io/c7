package org.camunda.community.rest.client

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer
import feign.Logger
import feign.Retryer
import feign.codec.Encoder
import org.camunda.community.rest.client.KeyChangingSpringManyMultipartFilesWriter.Companion.camundaMultipartFormEncoder
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.http.converter.autoconfigure.ClientHttpMessageConvertersCustomizer
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.support.FeignEncoderProperties
import org.springframework.cloud.openfeign.support.FeignHttpMessageConverters
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

/**
 * Basic configuration of the feign client.
 */
@AutoConfiguration
@EnableFeignClients
class FeignClientConfiguration {

  /**
   * Configure feign spring encoder for Camunda.
   */
  @Bean
  @ConditionalOnMissingBean
  fun camundaFeignEncoder(
    feignEncoderProperties: FeignEncoderProperties,
    converters: ObjectProvider<FeignHttpMessageConverters>
  ): Encoder = SpringEncoder(camundaMultipartFormEncoder(), feignEncoderProperties, converters)

  /**
   * Define a retryer for feign HTTP calls.
   */
  @Bean
  fun camundaRetryer() = Retryer.Default()

  /**
   * Configure feign logger level if corresponding property is set.
   */
  @Bean
  @ConditionalOnProperty("feign.client.config.default.loggerLevel")
  fun feignLoggerLevel(@Value("\${feign.client.config.default.loggerLevel}") defaultLogLevel: String) =
    Logger.Level.valueOf(defaultLogLevel)

  /**
   * Configures the Feign HTTP message converters using the provided converters and customizers.
   */
  @Bean
  fun feignHttpMessageConverters(
    customizers: ObjectProvider<ClientHttpMessageConvertersCustomizer>,
    cloudCustomizers: ObjectProvider<HttpMessageConverterCustomizer>
  ) = FeignHttpMessageConverters(customizers, cloudCustomizers)

/**
 * Configures http message converters customizer.
 * 
 * @return customizer.
 */
  @Bean
  fun camunda7ClientHttpMessageConvertersCustomizer() =
    ClientHttpMessageConvertersCustomizer {
      it.addCustomConverter(camunda7HttpMessageConverter())
    }

  /**
   * Create an object factory for the message converter with the customized object mapper.
   */
  private fun camunda7HttpMessageConverter(): HttpMessageConverter<*> {
    val builder = Jackson2ObjectMapperBuilder
      .json()
      .featuresToDisable(
        DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
      )
      .serializers(
        OffsetDateTimeSerializer(
          OffsetDateTimeSerializer.INSTANCE,
          false,
          DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
          JsonFormat.Shape.STRING
        ),
        DateSerializer(
          false,
          SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        )
      )
    return MappingJackson2HttpMessageConverter(builder.build())
  }
}

