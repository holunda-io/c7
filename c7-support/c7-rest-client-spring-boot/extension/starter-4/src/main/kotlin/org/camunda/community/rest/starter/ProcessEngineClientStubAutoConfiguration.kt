package org.camunda.community.rest.starter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.camunda.bpm.engine.DecisionService
import org.camunda.bpm.engine.ExternalTaskService
import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.ManagementService
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean

private val logger = KotlinLogging.logger {}
/**
 * Auto configuration providing client stub if no process engine is available.
 */
@AutoConfiguration(
  afterName = ["org.camunda.bpm.spring.boot.starter.CamundaBpmAutoConfiguration"]
)
@ConditionalOnProperty(prefix = "camunda.rest.client", name = ["enabled"], havingValue = "true", matchIfMissing = true)
class ProcessEngineClientStubAutoConfiguration {

  companion object

  /**
   * Sets up a fake engine if no engine is provided.
   */
  @Bean
  @ConditionalOnMissingBean(ProcessEngine::class)
  fun processEngineClientStub(
    decisionService: DecisionService,
    externalTaskService: ExternalTaskService,
    historyService: HistoryService,
    repositoryService: RepositoryService,
    runtimeService: RuntimeService,
    taskService: TaskService,
    managementService: ManagementService
  ): ProcessEngine {
    logger.info { "CAMUNDA-REST-STARTER-001: No existing process engine bean has been found. Providing a client-only stub." }
    return ProcessEngineConfigurationClientStub(
      decisionService,
      externalTaskService,
      historyService,
      repositoryService,
      runtimeService,
      taskService,
      managementService
    ).buildProcessEngine()
  }
}
