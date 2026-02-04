package org.camunda.community.rest.config

import feign.codec.ErrorDecoder
import org.camunda.community.rest.exception.CamundaHttpFeignErrorDecoder
import org.camunda.community.rest.exception.ClientExceptionFactory
import org.camunda.community.rest.exception.RemoteProcessEngineException
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean

/**
 * Configures error decoding.
 */
@AutoConfiguration
@ConditionalOnProperty(
  name = [
    "camunda.rest.client.error-encoding.enabled", // is still listed due to backwards compatibility
    "camunda.rest.client.error-decoding.enabled"
  ],
  matchIfMissing = true
)
class FeignErrorDecoderConfiguration {

  /**
   * Provides an error decoder bean for feign.
   * @param camundaRestClientProperties properties for configuration.
   */
  @Bean
  fun defaultErrorDecoder(camundaRestClientProperties: CamundaRestClientProperties): ErrorDecoder {
    return CamundaHttpFeignErrorDecoder(
      httpCodes = camundaRestClientProperties.errorDecoding.httpCodes,
      defaultDecoder = ErrorDecoder.Default(),
      wrapExceptions = camundaRestClientProperties.errorDecoding.wrapExceptions,
      targetExceptionType = RemoteProcessEngineException::class.java,
      exceptionFactory = object : ClientExceptionFactory<RemoteProcessEngineException> {
        override fun create(message: String, cause: Throwable?) = RemoteProcessEngineException(message, cause)
      }
    )
  }
}



