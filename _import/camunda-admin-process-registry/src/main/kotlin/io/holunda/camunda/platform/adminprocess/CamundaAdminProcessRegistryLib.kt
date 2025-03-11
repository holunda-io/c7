package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.engine.delegate.JavaDelegate

object CamundaAdminProcessRegistryLib {

  /**
   * Creates a new admin process.
   * @param activityId activity id of the service task.
   * @param label, optional label for the service task.
   * @param formFields optional list of form fields to be attached to start event.
   * @param historyTimeToLive history time to live for the admin process execution.
   * @param versionTag version tag of the deployed process.
   * @param tenantId optional tenant id to assign to the process.
   * @param delegate delegate to execute.
   * @return admin process instance.
   */
  @JvmStatic
  @JvmOverloads
  fun adminProcess(
    activityId: String,
    label: String = activityId,
    formFields: List<FormField<*>> = emptyList(),
    historyTimeToLive: Int = 0,
    versionTag: String = "1",
    tenantId: String = AdminProcessRegistry.DEFAULT_TENANT,
    delegate: JavaDelegate
  ): AdminProcess = AdminProcess
    .builder(activityId)
    .label(label)
    .addFormField(*formFields.toTypedArray())
    .historyTimeToLive(historyTimeToLive)
    .versionTag(versionTag)
    .tenantId(tenantId)
    .delegate(delegate)
    .build()
}
