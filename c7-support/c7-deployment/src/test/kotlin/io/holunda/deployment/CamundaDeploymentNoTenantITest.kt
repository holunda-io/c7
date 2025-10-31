package io.holunda.deployment

import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RepositoryService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.EnableMBeanExport
import org.springframework.jmx.support.RegistrationPolicy
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [CamundaDeploymentTestApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest-no-tenant")
@DirtiesContext
internal class CamundaDeploymentNoTenantITest {

  @Autowired
  private lateinit var repositoryService: RepositoryService

  @Test
  internal fun contextLoads() {
  }

  @Test
  internal fun `processes are deployed for both tenants`() {
    val deployments = repositoryService.createDeploymentQuery().list()

    assertThat(deployments).hasSize(1)
    assertThat(deployments.filter { it.tenantId == null }).hasSize(1)
    assertThat(repositoryService.createProcessDefinitionQuery().count()).isEqualTo(1)
  }
}
