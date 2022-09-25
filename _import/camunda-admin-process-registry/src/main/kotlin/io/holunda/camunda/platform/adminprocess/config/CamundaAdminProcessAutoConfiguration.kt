package io.holunda.camunda.platform.adminprocess.config

import io.holunda.camunda.platform.adminprocess.AdminProcess
import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry
import mu.KLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CamundaAdminProcessAutoConfiguration {
  companion object : KLogging()

  /**
   * Collects all beans of type [AdminProcess] in context and registers them in a map.
   */
  @Bean(AdminProcessRegistry.NAME)
  fun adminProcessRegistry(
    processes: List<AdminProcess>?
  ) = AdminProcessRegistry((processes ?: emptyList()).associateBy { it.activityId })

}
