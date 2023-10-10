package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmDataKotlin

data class BooleanField(
  override val id: String,
  override val label: String
) : FormField<Boolean>(id, label, null, "boolean", CamundaBpmDataKotlin.booleanVariable(id)) {

}
