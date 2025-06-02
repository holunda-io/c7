package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmData

/**
 * Defines a text field.
 * @param id id of the field.
 * @param label label of the field in the form.
 */
data class StringField(
  override val id: String,
  override val label: String
) : FormField<String>(id, label, null, "string", CamundaBpmData.stringVariable(id))
