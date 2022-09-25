package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.bpm.data.CamundaBpmData
import java.util.*

data class DateField(
  override val id: String,
  override val label: String
) : FormField<Date>(id, label, null, "date", CamundaBpmData.dateVariable(id))
