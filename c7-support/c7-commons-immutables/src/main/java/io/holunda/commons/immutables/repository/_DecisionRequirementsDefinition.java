package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.repository.DecisionDefinition;
import org.camunda.bpm.engine.repository.DecisionRequirementsDefinition;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#decisionRequirementsDefinition(DecisionRequirementsDefinition)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableDecisionRequirementsDefinition.class)
@JsonSerialize(as = ImmutableDecisionRequirementsDefinition.class)
@SuppressWarnings("java:S114")
public interface _DecisionRequirementsDefinition extends _ResourceDefinition, DecisionRequirementsDefinition {
  // all fields covered by _ResourceDefinition
}
