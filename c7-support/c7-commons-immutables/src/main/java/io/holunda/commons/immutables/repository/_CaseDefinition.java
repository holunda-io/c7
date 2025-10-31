package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.immutables.value.Value;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#caseDefinition(CaseDefinition)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableCaseDefinition.class)
@JsonSerialize(as = ImmutableCaseDefinition.class)
@SuppressWarnings("java:S114")
interface _CaseDefinition extends _ResourceDefinition, CaseDefinition {
  // empty
}
