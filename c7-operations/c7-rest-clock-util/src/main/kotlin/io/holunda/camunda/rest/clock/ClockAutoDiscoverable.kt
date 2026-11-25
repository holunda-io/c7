package io.holunda.camunda.rest.clock

import jakarta.ws.rs.RuntimeType
import jakarta.ws.rs.core.FeatureContext
import org.glassfish.jersey.internal.spi.AutoDiscoverable

/** Registers the clock resource automatically in Jersey server applications. */
class ClockAutoDiscoverable : AutoDiscoverable {

  /** Adds the resource once, leaving Jersey clients unchanged. */
  override fun configure(context: FeatureContext) {
    if (context.configuration.runtimeType == RuntimeType.SERVER &&
      !context.configuration.isRegistered(ClockRestService::class.java)) {
      context.register(ClockRestService::class.java)
    }
  }
}
