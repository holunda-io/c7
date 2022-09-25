package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmData

data class LongField(
  override val id: String,
  override val label: String
) : FormField<Long>(id, label, null, "long", CamundaBpmData.longVariable(id))
