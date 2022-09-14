package io.holunda.camunda.platform.adminprocess

import mu.KLogging
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

object CamundaAdminProcessRegistryLib {
  fun adminProcess(
    activityId: String,
    label: String = activityId,
    formFields: List<FormField<*>> = emptyList(),
    historyTimeToLive: Int = 0,
    versionTag: String = "1",
    delegate: JavaDelegate
  ): AdminProcess = object : AdminProcess(activityId, label, formFields, historyTimeToLive, versionTag) {
    override fun execute(execution: DelegateExecution) {
      delegate.execute(execution)
    }
  }
}

@Configuration
class CamundaAdminProcessAutoConfiguration {
  companion object : KLogging()

  /**
   * Collects all beans of type [AdminProcess] in context and registers them in a map.
   */
  @Bean(AdminProcessRegistry.NAME)
  fun adminProcessRegistry(
    processes: List<AdminProcess>?
  ) = AdminProcessRegistry((processes?: emptyList()).associateBy { it.activityId })

}
