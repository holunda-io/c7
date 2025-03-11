package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry.Companion.WARN
import io.holunda.camunda.platform.adminprocess.form.FormField
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder

/**
 * Defines the admin process super class to implement by the user.
 * Use [AdminProcess.builder] for instantiation.
 */
abstract class AdminProcess(
  val activityId: String,
  private val label: String,
  private val formFields: List<FormField<*>>,
  private val historyTimeToLive: Int,
  private val versionTag: String,
  val tenantId: String
) : JavaDelegate {

  /**
   * Static helper to retrieve the builder.
   */
  companion object {
    /**
     * Static builder to create new admin process.
     * @param activityId unique id of the service task to execute (used to identify process).
     */
    @JvmStatic
    fun builder(activityId: String) = Builder(activityId = activityId)
  }

  private val processName = "[admin] $label"

  val processDefinitionKey = "admin_$activityId"
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
      .camundaDelegateExpression("#{${AdminProcessRegistry.BEAN_NAME}}")
      .endEvent()
      .done()
  }

  override fun toString(): String {
    return "${this::class.simpleName ?: AdminProcess::class.simpleName}(activityName='$activityId', label='$label', processDefinitionKey='$processDefinitionKey', processName='$processName')"
  }

  /*
   * Add multiple form fields to builder.
   */
  private fun StartEventBuilder.camundaFormFields(formFields: List<FormField<*>>): StartEventBuilder = formFields
    .fold(this) { builder, formField -> builder.camundaFormField(formField) }

  /*
   * Add single form field to builder.
   */
  private fun StartEventBuilder.camundaFormField(formField: FormField<*>): StartEventBuilder = formField.addToStartEvent(this)


  /**
   * Builder for the admin process.
   * @param activityId activity id of the service task to execute.
   */
  class Builder(val activityId: String) {

    private var label: String = activityId
    private var formFields: MutableList<FormField<*>> = mutableListOf()
    private var historyTimeToLive: Int = 0
    private var versionTag: String = "1"
    private var tenantId: String = AdminProcessRegistry.DEFAULT_TENANT
    private var javaDelegate: JavaDelegate = WARN

    /**
     * Sets label.
     * @param label of the service task.
     * @return builder.
     */
    fun label(label: String) = apply { this.label = label }

    /**
     * Adds form fields.
     * @param formFields fields to add to the start event.
     * @return builder.
     */
    fun addFormField(vararg formFields: FormField<*>) = apply {
      formFields.forEach(this.formFields::add)
    }

    /**
     * Sets history TTL of the process.
     * @param historyTimeToLive history time to live.
     * @return builder.
     */
    fun historyTimeToLive(historyTimeToLive: Int) = apply { this.historyTimeToLive = historyTimeToLive }

    /**
     * Sets version tag of the deployed process.
     * @param versionTag version tag.
     * @return builder.
     */
    fun versionTag(versionTag: String) = apply { this.versionTag = versionTag }

    /**
     * Sets delegate for the service task.
     * @param delegate delegate to call on service task execution.
     * @return builder
     */
    fun delegate(delegate: JavaDelegate) = apply { this.javaDelegate = delegate }

    /**
     * Sets tenant id for the process.
     * @param tenantId tenant id to set.
     * @return builder.
     */
    fun tenantId(tenantId: String) = apply { this.tenantId = tenantId }

    /**
     * Builds the new admin process.
     * @return admin process instance.
     */
    fun build(): AdminProcess {
      AdminProcessRegistry.logger.debug { "Creating new admin process `$activityId` with delegate: ${javaDelegate.javaClass}" }
      return object :
        AdminProcess(
          activityId = activityId,
          label = label,
          formFields = formFields.toList(),
          historyTimeToLive = historyTimeToLive,
          versionTag = versionTag,
          tenantId = tenantId,
        ) {
        override fun execute(execution: DelegateExecution) {
          AdminProcessRegistry.logger.debug { "Executing anonymous admin process from builder delegate..." }
          javaDelegate.execute(execution)
          AdminProcessRegistry.logger.debug { "Executed anonymous admin process from builder delegate." }
        }
      }
    }
  }
}



