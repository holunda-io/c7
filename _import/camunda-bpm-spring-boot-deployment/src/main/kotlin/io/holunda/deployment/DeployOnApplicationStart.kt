package io.holunda.deployment

import mu.KLogging
import org.apache.commons.io.FilenameUtils
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.repository.DeploymentBuilder
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties
import org.springframework.context.event.EventListener
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.util.StopWatch

open class DeployOnApplicationStart(
  private val camundaDeployment: CamundaDeploymentProperties,
  private val repositoryService: RepositoryService
) {

  companion object : KLogging() {
    const val PROCESS_APPLICATION = "process application"
    val CAMUNDA_FILE_SUFFIXES = setOf(
      CamundaBpmProperties.DEFAULT_BPMN_RESOURCE_SUFFIXES.toSet(),
      CamundaBpmProperties.DEFAULT_CMMN_RESOURCE_SUFFIXES.toSet(),
      CamundaBpmProperties.DEFAULT_DMN_RESOURCE_SUFFIXES.toSet()
    ).flatten()
  }

  @EventListener
  fun accept(event: PostDeployEvent) {
    logger.info { "Starting Camunda deployment" }

    if (camundaDeployment.archives.isEmpty()) {
      logger.warn { "No process archives specified" }
    }

    camundaDeployment.archives.forEach { deployProcessArchive(it) }

    logger.info { "Camunda deployment finished" }
  }

  private fun deployProcessArchive(processArchive: CamundaDeploymentProperties.ProcessArchive) {
    logger.info { "Deploying process archive: [$processArchive]" }

    val resources = PathMatchingResourcePatternResolver().getResources("classpath*:${processArchive.path}/**/*.*")
      .filter { isCamundaResource(it) }

    if (resources.isNotEmpty()) {
      val deploymentBuilder = repositoryService.createDeployment()
        .name(processArchive.name)
        .source(PROCESS_APPLICATION)
        .enableDuplicateFiltering(false)
        .tenantId(processArchive.tenant)

      resources
        .forEach { addDeployment(processArchive, deploymentBuilder, it) }
      val stopWatch = StopWatch()

      stopWatch.start()
      deploymentBuilder.deploy()
      stopWatch.stop()

      logger.info { "Deployment of ${deploymentBuilder.resourceNames.size} resources took ${stopWatch.totalTimeSeconds} seconds" }
    } else {
      logger.warn { "No resources found in the specified path: [${processArchive.path}]" }
    }
  }

  private fun isCamundaResource(resource: Resource) =
    CAMUNDA_FILE_SUFFIXES.any { it.equals(FilenameUtils.getExtension(resource.filename)) }

  private fun addDeployment(
    processArchive: CamundaDeploymentProperties.ProcessArchive,
    deploymentBuilder: DeploymentBuilder,
    resource: Resource
  ) = sanitizePath(resource.uri.toString(), processArchive.path)
    .also { logger.info { "Adding resource: [$it]" } }
    .let { deploymentBuilder.addClasspathResource(it) }

  private fun sanitizePath(path: String, fragment: String) = path.substring(path.indexOf(fragment))
}
