package io.holunda.camunda.platform.adminprocess

import io.holunda.camunda.bpm.data.CamundaBpmData
import io.holunda.camunda.bpm.data.factory.VariableFactory
import java.util.*


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
) : VariableFactory<T> by variable

data class StringField(
  override val id: String,
  override val label: String
) : FormField<String>(id, label, null, "string", CamundaBpmData.stringVariable(id))

data class DateField(
  override val id: String,
  override val label: String
) : FormField<Date>(id, label, null, "date", CamundaBpmData.dateVariable(id))

