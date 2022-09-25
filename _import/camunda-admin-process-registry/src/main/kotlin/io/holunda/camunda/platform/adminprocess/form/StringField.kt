package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmData

data class StringField(
  override val id: String,
  override val label: String
) : FormField<String>(id, label, null, "string", CamundaBpmData.stringVariable(id))
