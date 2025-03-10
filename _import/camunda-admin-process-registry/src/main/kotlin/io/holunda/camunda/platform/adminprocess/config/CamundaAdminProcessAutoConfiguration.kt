package io.holunda.camunda.platform.adminprocess.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.holunda.camunda.platform.adminprocess.AdminProcess
import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.spring.boot.starter.CamundaBpmAutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

val logger = KotlinLogging.logger {}

@Configuration
@AutoConfigureAfter(CamundaBpmAutoConfiguration::class)
@Import(CamundaAdminProcessRegistryBinding::class)
class CamundaAdminProcessAutoConfiguration {

  /**
   * Collects all beans of type [AdminProcess] in context and registers them in a map.
   */
  @Bean(AdminProcessRegistry.DEFAULT_TENANT)
  fun adminProcessRegistry(
    processes: List<AdminProcess>?,
    repositoryService: RepositoryService
  ): AdminProcessRegistry {
    logger.info {"Initializing Admin Process Registry with ${processes?.size ?:0 } processes."}
    return AdminProcessRegistry(processes = (processes ?: emptyList()).associateBy { it.activityId }, repositoryService = repositoryService)
  }
}
