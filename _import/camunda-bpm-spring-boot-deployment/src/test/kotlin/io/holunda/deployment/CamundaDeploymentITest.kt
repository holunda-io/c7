package io.holunda.deployment

import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RepositoryService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [CamundaDeploymentTestApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
internal class CamundaDeploymentITest {

  @Autowired
  private lateinit var repositoryService: RepositoryService

  @Test
  internal fun contextLoads() {
  }

  @Test
  internal fun `processes are deployed for both tenants`() {
    val deployments = repositoryService.createDeploymentQuery().list()

    assertThat(deployments).hasSize(2)
    assertThat(deployments.filter { it.tenantId == null }).hasSize(1)
    assertThat(deployments.filter { it.tenantId == "foo" }).hasSize(1)
  }
}
