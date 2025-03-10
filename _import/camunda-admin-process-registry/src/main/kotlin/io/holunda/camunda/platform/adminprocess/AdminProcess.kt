package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.CamundaAdminProcessRegistryLib.camundaFormFields
import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance

abstract class AdminProcess(
  val activityId: String,
  private val label: String = activityId,
  private val formFields: List<FormField<*>>,
  private val historyTimeToLive: Int = 0,
  private val versionTag: String = "1",
  val tenantId: String = AdminProcessRegistry.DEFAULT_TENANT
) : JavaDelegate {
  companion object {
    @JvmStatic
    fun builder(activityId: String) = Builder(activityId = activityId)
  }

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
      .camundaDelegateExpression("#{${AdminProcessRegistry.DEFAULT_TENANT}}")
      .endEvent()
      .done()
  }

  override fun toString(): String {
    return "${this::class.simpleName ?: AdminProcess::class.simpleName}(activityName='$activityId', label='$label', processDefinitionKey='$processDefinitionKey', processName='$processName')"
  }

  class Builder(val activityId: String) {

    private var label: String = activityId
    private var formFields: MutableList<FormField<*>> = mutableListOf()
    private var historyTimeToLive: Int = 0
    private var versionTag: String = "1"
    private lateinit var javaDelegate: JavaDelegate

    fun label(label: String) = apply { this.label = label }

    fun addFormField(vararg formFields: FormField<*>) = apply {
      formFields.forEach(this.formFields::add)
    }

    fun historyTimeToLive(historyTimeToLive: Int) = apply { this.historyTimeToLive = historyTimeToLive }
    fun versionTag(versionTag: String) = apply { this.versionTag = versionTag }
    fun delegate(delegate: JavaDelegate) = apply { this.javaDelegate = delegate }

    fun build(): AdminProcess {
      check(this::javaDelegate.isInitialized) { "JavaDelegate is not initialized." }

      return object :
        AdminProcess(activityId = activityId, label = label, formFields = formFields.toList(), historyTimeToLive = historyTimeToLive, versionTag = versionTag) {
        override fun execute(execution: DelegateExecution) {
          javaDelegate.execute(execution)
        }
      }
    }
  }
}



