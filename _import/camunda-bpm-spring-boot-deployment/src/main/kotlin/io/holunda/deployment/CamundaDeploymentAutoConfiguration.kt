package io.holunda.deployment

import org.camunda.bpm.engine.RepositoryService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(DeployOnApplicationStart::class)
@EnableConfigurationProperties(value = [CamundaDeploymentProperties::class])
class CamundaDeploymentAutoConfiguration {

  @Bean
  @ConditionalOnProperty(prefix = "camunda.bpm.deployment", value = ["enabled"], havingValue = "true", matchIfMissing = true)
  fun deployOnApplicationStart(camundaDeploymentProperties: CamundaDeploymentProperties, repositoryService: RepositoryService) =
    DeployOnApplicationStart(camundaDeploymentProperties, repositoryService)
}
