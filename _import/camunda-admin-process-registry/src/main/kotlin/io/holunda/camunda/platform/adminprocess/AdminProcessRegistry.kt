package io.holunda.camunda.platform.adminprocess

import io.github.oshai.kotlinlogging.KotlinLogging
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.repository.Deployment
import java.util.UUID



class AdminProcessRegistry(
  private val processes: Map<ActivityId, AdminProcess>,
  private val repositoryService: RepositoryService
) : JavaDelegate {
  companion object {

    val logger = KotlinLogging.logger {}

    const val DEFAULT_TENANT = "adminProcessRegistry"

    /**
     * Implementation of [AdminProcess] that does not get deployed and is only used as a fallback, when
     * the engine tries to run an activity that is not registered.
     */
    val WARN = CamundaAdminProcessRegistryLib.adminProcess("WARN") {
      logger.warn { "No adminProcess registered with processDefinitionKey=${it.currentActivityId}" }
    }
  }

  fun deploy() {
    if (processes.isNotEmpty()) {
      logger.info { "Deploying admin processes: ${processes.values}" }
      createDeployment(repositoryService)
    } else
      logger.info { "No admin processes registered - skipping deployment." }
  }

  private fun createDeployment(repositoryService: RepositoryService): List<Deployment> {
    return processes
      .values
      .groupBy { it.tenantId }
      .map { (tenantId, adminProcesses) ->
      repositoryService
        .createDeployment()
        .name("Admin-${UUID.randomUUID()}")
        .tenantId(tenantId)
        .enableDuplicateFiltering(true)
        .let { builder ->
          adminProcesses.forEach { process ->
            builder.addModelInstance("${process.processDefinitionKey}.bpmn", process.modelInstance,)
          }
          builder
        }.deploy()
      }
  }


  /**
   * Delegates the execution to a registered admin process.
   */
  override fun execute(execution: DelegateExecution): Unit = processes.getOrDefault(execution.currentActivityId, WARN).execute(execution)

}
