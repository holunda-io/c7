package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.repository.DecisionDefinition;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#decisionDefinition(DecisionDefinition)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableDecisionDefinition.class)
@JsonSerialize(as = ImmutableDecisionDefinition.class)
@SuppressWarnings("java:S114")
public interface _DecisionDefinition extends _ResourceDefinition, DecisionDefinition {
  @Override
  @Nullable
  String getDecisionRequirementsDefinitionId();

  @Override
  @Nullable
  String getDecisionRequirementsDefinitionKey();

  @Override
  @Nullable
  String getVersionTag();
}
