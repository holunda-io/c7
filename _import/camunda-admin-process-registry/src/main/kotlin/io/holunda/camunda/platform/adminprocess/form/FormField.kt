package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.factory.VariableFactory
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder

sealed class FormField<T>(
  /**
   * id of the formField, name of the variable
   */
  open val id: String,
  /**
   * label of the variable
   */
  open val label: String = id,
  /**
   * optional default value
   */
  open val defaultValue: T? = null,

  val type: String,
  /**
   * variable factory for simplified access.
   */
  private val variable: VariableFactory<T>
) : VariableFactory<T> by variable {

  fun addToStartEvent(startEventBuilder: StartEventBuilder): StartEventBuilder = startEventBuilder.camundaFormField()
    .camundaLabel(label)
    .camundaType(type)
    .camundaId(id)
    //.camundaDefaultValue(defaultValue?.toString())
    .camundaFormFieldDone()

}
