package io.holunda.camunda.platform.adminprocess

import mu.KLogging
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.repository.Deployment
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.springframework.context.event.EventListener


class AdminProcessRegistry(
  private val processes: Map<ActivityId, AdminProcess>
) : JavaDelegate {
  companion object : KLogging() {

    const val NAME = "adminProcessRegistry"

    /**
     * Implementation of [AdminProcess] that does not get deployed and is only used as a fallback, when
     * the engine tries to run an activity that is not registered.
     */
    val WARN = CamundaAdminProcessRegistryLib.adminProcess("WARN") {
      logger.warn { "no adminProcess registered with processDefinitionKey=${it.currentActivityId}" }
    }

  }

  @EventListener
  fun deploy(evt: PostDeployEvent) {
    if (processes.isNotEmpty()) {
      logger.info { "deploying admin processes: ${processes.values}" }
      createDeployment(evt.processEngine.repositoryService)
    } else
      logger.info { "no admin processes registered - skip deployment." }
  }

  fun createDeployment(repositoryService: RepositoryService, tenantId: String? = NAME): Deployment =
    processes.values.fold(repositoryService.createDeployment()) { builder, process ->
      builder.addModelInstance(
        "${process.processDefinitionKey}.bpmn",
        process.modelInstance
      )
    }.tenantId(tenantId)
      .enableDuplicateFiltering(true)
      .deploy()

  /**
   * Delegates the execution to a registered admin process.
   */
  override fun execute(execution: DelegateExecution): Unit = processes.getOrDefault(execution.currentActivityId, WARN).execute(execution)

}
