package io.holunda.camunda.platform.adminprocess.config

import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * Binds the registry and executed the deployment on process application start.
 */
@Component
class CamundaAdminProcessRegistryBinding(
  private val adminProcessRegistry: AdminProcessRegistry
) {

  /**
   * Executes deployment, if any processes are defined.
   */
  @EventListener
  fun on(evt: PostDeployEvent) {
    adminProcessRegistry.deploy()
  }
}
