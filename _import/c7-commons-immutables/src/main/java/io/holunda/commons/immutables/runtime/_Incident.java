package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.CurrentTimestamp;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.Incident;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableIncident.class)
@JsonSerialize(as = ImmutableIncident.class)
@SuppressWarnings("java:S114")
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

  @Override
  @Nullable
  String getFailedActivityId();
}
