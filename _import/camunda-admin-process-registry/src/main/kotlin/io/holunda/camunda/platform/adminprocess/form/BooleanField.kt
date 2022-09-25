package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmData

data class BooleanField(
  override val id: String,
  override val label: String
) : FormField<Boolean>(id, label, null, "boolean", CamundaBpmData.booleanVariable(id)) {

}
