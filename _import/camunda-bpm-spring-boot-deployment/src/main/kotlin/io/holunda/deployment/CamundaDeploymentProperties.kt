package io.holunda.deployment

import jakarta.validation.ValidationException
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("camunda.bpm.deployment")
@ConditionalOnProperty(prefix = "camunda.bpm.deployment", value = ["enabled"], havingValue = "true", matchIfMissing = true)
data class CamundaDeploymentProperties(
  /**
   * Flag to activate (by defaults) or deactivate the library.
   */
  val enabled: Boolean = true,
  /**
   * Allows definition of tenants with overlapping classpath locations. Defaults to `false`.
   */
  val allowOverlapping: Boolean = false,
  /**
   * List of process archives to be deployed, defaults to an empty list.
   */
  val archives: List<ProcessArchive> = emptyList()
) {

  init {
    this.archives.groupBy { it.name }.forEach {
      if (it.value.size > 1)
        throw ProcessArchiveNameNotUniqueException("Name [${it.key}] is not unique within process archives [$archives]")
    }

    this.archives.groupBy { it.tenant }.forEach {
      if (it.value.size > 1)
        throw ProcessArchiveTenantNotUniqueException("Tenant [${it.key}] is not unique within process archives [$archives]")
    }
  }

  data class ProcessArchive(
    val name: String,
    val tenant: String? = null,
    val path: String
  )
}

class ProcessArchiveNameNotUniqueException(message: String) : ValidationException(message)
class ProcessArchiveTenantNotUniqueException(message: String) : ValidationException(message)
