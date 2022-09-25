package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.CamundaAdminProcessRegistryLib.camundaFormFields
import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance

abstract class AdminProcess(
  val activityId: String,
  private val label: String = activityId,
  private val formFields: List<FormField<*>>,
  private val historyTimeToLive: Int = 0,
  private val versionTag: String = "1"
) : JavaDelegate {

  val processDefinitionKey = "admin_$activityId"
  val processName = "[admin] $label"

  val modelInstance: BpmnModelInstance by lazy {
    Bpmn.createExecutableProcess(processDefinitionKey)
      .name(processName)
      .camundaHistoryTimeToLive(historyTimeToLive)
      .camundaVersionTag(versionTag)
      .camundaStartableInTasklist(true)
      .startEvent()
      .camundaFormFields(formFields)
      .serviceTask(activityId)
      .camundaAsyncBefore()
      .name(label)
      .camundaDelegateExpression("#{${AdminProcessRegistry.NAME}}")
      .endEvent()
      .done()
  }

  override fun toString(): String {
    return "${this::class.simpleName ?: AdminProcess::class.simpleName}(activityName='$activityId', label='$label', processDefinitionKey='$processDefinitionKey', processName='$processName')"
  }
}



