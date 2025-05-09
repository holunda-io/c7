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

@SpringBootTest(
  classes = [CamundaDeploymentTestApplication::class],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("itest-overlapping")
@DirtiesContext
internal class CamundaDeploymentOverlappingITest {

  @Autowired
  private lateinit var repositoryService: RepositoryService

  @Test
  fun contextLoads() {
  }

  @Test
  fun `processes are deployed for all tenants`() {
    val deployments = repositoryService.createDeploymentQuery().list()

    assertThat(deployments).hasSize(3)
    assertThat(deployments.filter { it.tenantId == null }).hasSize(1)
    assertThat(deployments.filter { it.tenantId == "bar" }).hasSize(1)
    assertThat(deployments.filter { it.tenantId == "foo" }).hasSize(1)

    assertThat(repositoryService.createProcessDefinitionQuery().withoutTenantId().count()).isEqualTo(1)
    assertThat(repositoryService.createProcessDefinitionQuery().tenantIdIn("bar").count()).isEqualTo(1)
    assertThat(repositoryService.createProcessDefinitionQuery().tenantIdIn("foo").count()).isEqualTo(1)
  }
}
