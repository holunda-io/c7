package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder

object CamundaAdminProcessRegistryLib {

  @JvmStatic
  fun adminProcess(
    activityId: String,
    label: String = activityId,
    formFields: List<FormField<*>> = emptyList(),
    historyTimeToLive: Int = 0,
    versionTag: String = "1",
    delegate: JavaDelegate
  ): AdminProcess = object : AdminProcess(activityId, label, formFields, historyTimeToLive, versionTag) {
    override fun execute(execution: DelegateExecution) {
      delegate.execute(execution)
    }
  }

  /**
   * Add single form field to builder.
   */
  fun StartEventBuilder.camundaFormField(formField: FormField<*>): StartEventBuilder = formField.addToStartEvent(this)

  /**
   * Add multiple form fields to builder.
   */
  fun StartEventBuilder.camundaFormFields(formFields: List<FormField<*>>): StartEventBuilder = formFields
    .fold(this) { builder, formField -> builder.camundaFormField(formField) }
}
