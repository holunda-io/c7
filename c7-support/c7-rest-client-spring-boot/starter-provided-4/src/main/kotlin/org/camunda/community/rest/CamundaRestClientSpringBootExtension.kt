package org.camunda.community.rest

import org.camunda.community.rest.client.FeignClientConfiguration
import org.camunda.community.rest.config.CamundaRemoteServicesConfiguration
import org.camunda.community.rest.config.CamundaRestClientProperties
import org.camunda.community.rest.config.FeignErrorDecoderConfiguration
import org.camunda.community.rest.variables.ValueMapperConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


/**
 * Basic configuration of the extension.
 */
@Configuration
@ConditionalOnProperty(prefix = "camunda.rest.client", name = ["enabled"], havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(CamundaRestClientProperties::class)
@ImportAutoConfiguration(
  FeignClientConfiguration::class,
  FeignErrorDecoderConfiguration::class,
  ValueMapperConfiguration::class,
  CamundaRemoteServicesConfiguration::class,
)
class CamundaRestClientSpringBootExtension


/**
 * Enables the registration of REST client beans.
 */
@Import(CamundaRestClientSpringBootExtension::class)
annotation class EnableCamundaRestClient
