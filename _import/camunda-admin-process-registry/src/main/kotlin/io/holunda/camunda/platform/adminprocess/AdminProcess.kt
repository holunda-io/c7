package io.holunda.camunda.platform.adminprocess

import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder


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
      .camundaAsyncAfter()
      .serviceTask(activityId)
      .name(label)
      .camundaDelegateExpression("#{${AdminProcessRegistry.NAME}}")
      .endEvent()
      .done()
  }

  override fun toString(): String {
    return "${this::class.simpleName ?: AdminProcess::class.simpleName}(activityName='$activityId', label='$label', processDefinitionKey='$processDefinitionKey', processName='$processName')"
  }
}


fun StartEventBuilder.camundaFormFields(formFields: List<FormField<*>>): StartEventBuilder = formFields.fold(this) { builder, formField ->
  builder.camundaFormField()
    .camundaLabel(formField.label)
    .camundaType(formField.type)
    .camundaId(formField.id)
    .camundaDefaultValue(formField.defaultValue?.toString())
    .camundaFormFieldDone()
}

