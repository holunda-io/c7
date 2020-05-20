package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableCaseExecution.class)
@JsonSerialize(as = ImmutableCaseExecution.class)
public interface  _CaseExecution extends CaseExecution {

  @Override
  @Nullable
  String getCaseInstanceId();

  @Override
  @Nullable
  String getCaseDefinitionId();

  @Override
  @Nullable
  String getActivityId();

  @Override
  @Nullable
  String getActivityName();

  @Override
  @Nullable
  String getActivityType();

  @Override
  @Nullable
  String getActivityDescription();

  @Override
  @Nullable
  String getParentId();

  @Override
  default boolean isRequired() {
    return false;
  }

  @Override
  default boolean isAvailable() {
    return false;
  }

  @Override
  default boolean isActive() {
    return false;
  }

  @Override
  default boolean isEnabled() {
    return false;
  }

  @Override
  default boolean isDisabled() {
    return false;
  }

  @Override
  default boolean isTerminated() {
    return false;
  }

  @Override
  @Nullable
  String getTenantId();
}
