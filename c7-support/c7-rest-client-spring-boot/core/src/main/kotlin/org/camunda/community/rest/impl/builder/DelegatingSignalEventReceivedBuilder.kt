

package org.camunda.community.rest.impl.builder

import org.camunda.bpm.engine.runtime.SignalEventReceivedBuilder
import org.camunda.community.rest.client.api.SignalApiClient
import org.camunda.community.rest.client.model.SignalDto
import org.camunda.community.rest.variables.ValueMapper

/**
 * Correlation builder, collecting all settings in the DTO sent to the REST endpoint later.
 */
class DelegatingSignalEventReceivedBuilder(
  signalName: String,
  private val signalApiClient: SignalApiClient,
  private val valueMapper: ValueMapper
) : SignalEventReceivedBuilder {

  private val signalDto = SignalDto().apply {
    this.name = signalName
    this.variables = mutableMapOf()
  }

  override fun setVariables(variables: MutableMap<String, Any>): SignalEventReceivedBuilder {
    signalDto.variables = valueMapper.mapValues(variables)
    return this
  }

  override fun tenantId(tenantId: String): SignalEventReceivedBuilder {
    signalDto.tenantId = tenantId
    return this
  }

  override fun executionId(executionId: String): SignalEventReceivedBuilder {
    signalDto.executionId = executionId
    return this
  }

  override fun withoutTenantId(): SignalEventReceivedBuilder {
    signalDto.withoutTenantId = true
    return this
  }

  override fun send() {
    signalApiClient.throwSignal(signalDto)
  }

}
