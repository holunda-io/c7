package io.holunda.deployment

import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RepositoryService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [CamundaDeploymentTestApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest-without-archive-tenant-uniqueness")
@DirtiesContext
internal class CamundaDeploymentWithoutArchiveTenantUniquenessITest {

  @Autowired
  private lateinit var repositoryService: RepositoryService

  @Test
  internal fun contextLoads() {
  }

  @Test
  internal fun `archives are deployed for the same tenant`() {
    val deployments = repositoryService.createDeploymentQuery().list()

    assertThat(deployments).hasSize(2)
    assertThat(deployments.filter { it.tenantId == null }).hasSize(2)
  }
}
