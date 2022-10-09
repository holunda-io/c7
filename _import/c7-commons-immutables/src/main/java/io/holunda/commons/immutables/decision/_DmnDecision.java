package io.holunda.commons.immutables.decision;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import io.holunda.commons.immutables.externaltask.ImmutableExternalTask;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionLogic;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.immutables.value.Value;

import java.util.Collection;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#externalTask(ExternalTask)
 */
@Value.Immutable
@CamundaPojoStyle
//@JsonDeserialize(as = ImmutableExternalTask.class)
//@JsonSerialize(as = ImmutableExternalTask.class)
@SuppressWarnings("java:S114")
interface _DmnDecision extends DmnDecision {

  @Override
  String getKey();

  @Override
  String getName();

  @Override
  boolean isDecisionTable();

  @Override
  DmnDecisionLogic getDecisionLogic();

  @Override
  Collection<DmnDecision> getRequiredDecisions();
}
