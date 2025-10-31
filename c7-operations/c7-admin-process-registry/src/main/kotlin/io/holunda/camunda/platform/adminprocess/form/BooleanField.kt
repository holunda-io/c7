package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmDataKotlin

/**
 * Defines a checkbox field.
 * @param id id of the field.
 * @param label label of the field in the form.
 */
data class BooleanField(
  override val id: String,
  override val label: String
) : FormField<Boolean>(id, label, null, "boolean", CamundaBpmDataKotlin.booleanVariable(id).nonNull)
