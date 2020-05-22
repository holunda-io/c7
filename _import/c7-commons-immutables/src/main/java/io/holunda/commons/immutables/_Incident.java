package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CurrentTimestamp;
import java.util.Date;
import org.camunda.bpm.engine.runtime.Incident;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableIncident.class)
@JsonSerialize(as = ImmutableIncident.class)
interface _Incident extends Incident, CurrentTimestamp {

  @Override
  default Date getIncidentTimestamp() {
    return getNow().get();
  }

  @Override
  @Nullable
  String getIncidentMessage();

  @Override
  @Nullable
  String getTenantId();

  @Override
  @Nullable
  String getConfiguration();

  @Override
  @Nullable
  String getActivityId();

  @Override
  @Nullable
  String getCauseIncidentId();

  @Override
  @Nullable
  String getRootCauseIncidentId();

  @Override
  @Nullable
  String getHistoryConfiguration();
}
