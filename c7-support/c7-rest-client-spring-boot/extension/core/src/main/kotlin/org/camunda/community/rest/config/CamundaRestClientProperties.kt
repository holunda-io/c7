package org.camunda.community.rest.config

import org.camunda.community.rest.variables.CamundaRestClientVariablesProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty

/**
 * Configuration properties for Camunda REST Client Spring Boot.
 * @constructor creates the properties.
 * @param errorDecoding configuration of error decoding of HTTP response codes.
 */
@ConfigurationProperties("camunda.rest.client")
data class CamundaRestClientProperties(
  /**
   * Enables (by default `true`) or disables the entire configuration of the extension.
   */
  val enabled: Boolean = true,
  /**
   * Controls error decoding from HTTP response codes.
   */
  @NestedConfigurationProperty
  val errorDecoding: ErrorDecoding = ErrorDecoding(),

  /**
   * Controls whether variables should be deserialized on the server side, if requesting deserialization.
   * For this to work, the classes all have to be known on the server side.
   * Variables will then be deserialized and again serialized with jackson to send them as JSON.
   */
  val deserializeVariablesOnServer: Boolean = false,

  /**
   * Allows configuration of variable handling specific properties, such as serialization format.
   */
  @NestedConfigurationProperty
  val variables: CamundaRestClientVariablesProperties = CamundaRestClientVariablesProperties()
) {
  /**
   * Controls decoding of HTTP status response to Camunda Exceptions.
   */
  data class ErrorDecoding(
    /**
     * Enable decoding.
     */
    val enabled: Boolean = true,
    /**
     * List of HTTP codes to decode. Defaults to HTTP status 400 and 500.
     */
    val httpCodes: List<Int> = listOf(400, 500),
    /**
     * Wrap exceptions in RemoteProcessException even when specific camunda exceptions is decoded.
     */
    val wrapExceptions: Boolean = true
  )

}

