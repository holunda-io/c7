package io.holunda.camunda.platform.adminprocess

import io.github.oshai.kotlinlogging.KotlinLogging
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.repository.Deployment
import org.camunda.bpm.model.xml.impl.util.IoUtil


/**
 * Registry holding the list of admin processes and responsible for deployment and dispatching of the execution.
 */
class AdminProcessRegistry(
  private val processes: Map<ActivityId, AdminProcess>,
  private val repositoryService: RepositoryService
) : JavaDelegate {

  /**
   * Static constant holder.
   */
  companion object {

    val logger = KotlinLogging.logger {}

      const val DEFAULT_TENANT = "tenant-admin-process-registry"
    const val BEAN_NAME = "adminProcessRegistry"

    /**
     * Implementation of [AdminProcess] that does not get deployed and is only used as a fallback, when
     * the engine tries to run an activity that is not registered.
     */
    val WARN = CamundaAdminProcessRegistryLib
      .adminProcess("WARN") { delegateExecution ->
        logger.warn { "No adminProcess registered with processDefinitionKey=${delegateExecution.currentActivityId}" }
      }
  }

  /**
   * Delegates the execution to a registered admin process.
   */
  override fun execute(execution: DelegateExecution) {
    logger.debug { "Running admin dispatching delegate for ${execution.currentActivityId}." }
    processes.getOrDefault(execution.currentActivityId, WARN).execute(execution)
    logger.debug { "Completed admin dispatching delegate." }
  }


  /**
   * Deploys registered admin processes.
   */
  fun deploy() {
    if (processes.isNotEmpty()) {
      logger.info { "Deploying admin processes: ${processes.values}" }
      createDeployment(repositoryService)
    } else
      logger.info { "No admin processes registered, skipping deployment." }
  }

  private fun createDeployment(repositoryService: RepositoryService): List<Deployment> {
    return processes
      .values
      .groupBy { it.tenantId }
      .map { (tenantId, adminProcesses) ->

      repositoryService
        .createDeployment()
        .name("AdminProcessRegistry" + if(tenantId != DEFAULT_TENANT) { "-${tenantId}" } else { "" })
        .let { builder ->
          adminProcesses.forEach { process ->
            logger.debug { "Deploying process ${process.processDefinitionKey} with model: \n${IoUtil.convertXmlDocumentToString(process.modelInstance.document)}\n" }
            builder.addModelInstance("${process.processDefinitionKey}.bpmn", process.modelInstance)
          }
          if (tenantId != DEFAULT_TENANT) {
            builder.tenantId(tenantId)
          }
          builder
        }
        .enableDuplicateFiltering(true)
        .deploy()
      }
  }

}
