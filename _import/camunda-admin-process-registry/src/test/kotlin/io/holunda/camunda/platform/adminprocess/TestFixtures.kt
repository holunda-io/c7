package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance


object TestFixtures {

  fun createProcess(field: FormField<*>) = Bpmn.createExecutableProcess()
    .startEvent()
    .apply { field.addToStartEvent(this) }
    .endEvent()
    .done()


  fun BpmnModelInstance.xml() = Bpmn.convertToString(this)

  fun String.extensionElements() = this
    .substringAfter("<extensionElements>")
    .substringBefore("</extensionElements>")
    .flatten()
    .trim().trimIndent()

  fun String.flatten() = this.lines().map { it.trim() }
    .joinToString("\n")
}
