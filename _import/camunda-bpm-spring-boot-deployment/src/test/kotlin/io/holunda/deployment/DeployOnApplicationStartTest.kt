package io.holunda.deployment

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.repository.Deployment
import org.camunda.bpm.engine.repository.DeploymentBuilder
import org.camunda.bpm.engine.repository.DeploymentWithDefinitions
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.cmmn.CmmnModelInstance
import org.camunda.bpm.model.dmn.DmnModelInstance
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.junit.jupiter.api.Test
import java.io.InputStream
import java.util.*
import java.util.zip.ZipInputStream

internal class DeployOnApplicationStartTest {

  private val repositoryService: RepositoryService = mockk()

  @Test
  internal fun `deploy specified process archive with resources`() {
    val processArchive = CamundaDeploymentProperties.ProcessArchive("foo", "foo", "tenants/foo")
    val properties = CamundaDeploymentProperties(listOf(processArchive))
    val deployOnApplicationStart = DeployOnApplicationStart(properties, repositoryService)

    val deploymentBuilder = DeploymentBuilderFake()
    every { repositoryService.createDeployment() } returns deploymentBuilder

    deployOnApplicationStart.accept(PostDeployEvent(mockk()))

    verify(exactly = 1) { repositoryService.createDeployment() }
    assertThat(deploymentBuilder.deployed).isTrue
    assertThat(deploymentBuilder.resourceNames).hasSize(1)
    assertThat(deploymentBuilder.resourceNames[0]).isEqualTo("${processArchive.path}/dummyProcess.bpmn")
    assertThat(deploymentBuilder.tenantId!!).isEqualTo(processArchive.tenant)
    assertThat(deploymentBuilder.name!!).isEqualTo(processArchive.name)
  }

  @Test
  internal fun `deploy nothing if no ProcessArchives are specified`() {
    val deployOnApplicationStart = DeployOnApplicationStart(CamundaDeploymentProperties(emptyList()), repositoryService)

    deployOnApplicationStart.accept(PostDeployEvent(mockk()))

    verify(exactly = 0) { repositoryService.createDeployment() }
  }

  @Test
  internal fun `Do not deploy anything if no resources exist`() {
    val deployOnApplicationStart = DeployOnApplicationStart(
      CamundaDeploymentProperties(
        listOf(
          CamundaDeploymentProperties.ProcessArchive("test", "test", "test/does_not_exist")
        )
      ),
      repositoryService
    )

    deployOnApplicationStart.accept(PostDeployEvent(mockk()))

    verify(exactly = 0) { repositoryService.createDeployment() }
  }
}

class DeploymentBuilderFake(
  var deployed: Boolean = false,
  var resourceNames: MutableList<String> = mutableListOf(),
  var tenantId: String? = null,
  var name: String? = null
) : DeploymentBuilder {
  override fun addInputStream(resourceName: String?, inputStream: InputStream?): DeploymentBuilder {
    return this
  }

  override fun addClasspathResource(resource: String): DeploymentBuilder {
    resourceNames.add(resource)
    return this
  }

  override fun addString(resourceName: String?, text: String?): DeploymentBuilder {
    return this
  }

  override fun addModelInstance(resourceName: String?, modelInstance: BpmnModelInstance?): DeploymentBuilder {
    return this
  }

  override fun addModelInstance(resourceName: String?, modelInstance: DmnModelInstance?): DeploymentBuilder {
    return this
  }

  override fun addModelInstance(resourceName: String?, modelInstance: CmmnModelInstance?): DeploymentBuilder {
    return this
  }

  override fun addZipInputStream(zipInputStream: ZipInputStream?): DeploymentBuilder {
    return this
  }

  override fun addDeploymentResources(deploymentId: String?): DeploymentBuilder {
    return this
  }

  override fun addDeploymentResourceById(deploymentId: String?, resourceId: String?): DeploymentBuilder {
    return this
  }

  override fun addDeploymentResourcesById(deploymentId: String?, resourceIds: MutableList<String>?): DeploymentBuilder {
    return this
  }

  override fun addDeploymentResourceByName(deploymentId: String?, resourceName: String?): DeploymentBuilder {
    return this
  }

  override fun addDeploymentResourcesByName(deploymentId: String?, resourceNames: MutableList<String>?): DeploymentBuilder {
    return this
  }

  override fun name(name: String): DeploymentBuilder {
    this.name = name
    return this
  }

  override fun nameFromDeployment(deploymentId: String?): DeploymentBuilder {
    return this
  }

  @Deprecated("delegates to deprecated method.", replaceWith = ReplaceWith("DeploymentBuilder#enableDuplicateFiltering(deployChangedOnly: Boolean)"))
  override fun enableDuplicateFiltering(): DeploymentBuilder {
    return this
  }

  override fun enableDuplicateFiltering(deployChangedOnly: Boolean): DeploymentBuilder {
    return this
  }

  override fun activateProcessDefinitionsOn(date: Date?): DeploymentBuilder {
    return this
  }

  override fun source(source: String?): DeploymentBuilder {
    return this
  }

  override fun deploy(): Deployment {
    deployed = true
    return DeploymentFake()
  }

  override fun deployWithResult(): DeploymentWithDefinitions {
    TODO("Not yet implemented")
  }

  override fun getResourceNames(): MutableCollection<String> {
    return resourceNames
  }

  override fun tenantId(tenantId: String): DeploymentBuilder {
    this.tenantId = tenantId
    return this
  }
}

class DeploymentFake : Deployment {
  override fun getId(): String {
    TODO("Not yet implemented")
  }

  override fun getName(): String {
    TODO("Not yet implemented")
  }

  override fun getDeploymentTime(): Date {
    TODO("Not yet implemented")
  }

  override fun getSource(): String {
    TODO("Not yet implemented")
  }

  override fun getTenantId(): String {
    TODO("Not yet implemented")
  }
}
