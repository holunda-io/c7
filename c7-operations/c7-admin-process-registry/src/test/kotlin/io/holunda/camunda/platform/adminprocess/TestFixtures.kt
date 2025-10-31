package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance


object TestFixtures {

  /**
   * Creates a new process with given form fiels.
   * @param field form field.
   * @return process.
   */
  fun createProcess(field: FormField<*>) = Bpmn.createExecutableProcess()
    .startEvent()
    .apply { field.addToStartEvent(this) }
    .endEvent()
    .done()


  /**
   * Creates string representation of the BPMN model.
   * @return string representation.
   */
  fun BpmnModelInstance.xml(): String = Bpmn.convertToString(this)

  /**
   * Retrieves the content of extension element block.
   */
  fun String.extensionElements() = this
    .substringAfter("<extensionElements>")
    .substringBefore("</extensionElements>")
    .flatten()
    .trim()
    .trimIndent()

  /**
   * Removes new lines from the string.
   */
  fun String.flatten() = this.lines().joinToString("\n") { it.trim() }
}
