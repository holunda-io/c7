package io.holunda.camunda.platform.adminprocess.config

import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CamundaAdminProcessRegistryBinding(
  private val adminProcessRegistry: AdminProcessRegistry
) {

  @EventListener
  fun on(evt: ProcessApplicationStartedEvent) {
    adminProcessRegistry.deploy()
  }
}
