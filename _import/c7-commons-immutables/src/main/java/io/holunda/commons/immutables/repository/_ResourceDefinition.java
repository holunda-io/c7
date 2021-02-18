package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.repository.ResourceDefinition;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableResourceDefinition.class)
@JsonSerialize(as = ImmutableResourceDefinition.class)
@SuppressWarnings("java:S114")
interface _ResourceDefinition extends ResourceDefinition {

  @Override
  String getId();

  @Override
  String getCategory();

  @Override
  String getName();

  @Override
  String getKey();

  @Override
  default int getVersion() {
    return 0;
  }

  @Override
  String getResourceName();

  @Override
  String getDeploymentId();

  @Override
  String getDiagramResourceName();

  @Override
  @Nullable
  String getTenantId();

  @Override
  @Nullable
  Integer getHistoryTimeToLive();
}
