package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.repository.*;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#deployment(Deployment)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableDeployment.class)
@JsonSerialize(as = ImmutableDeployment.class)
@SuppressWarnings("java:S114")
public interface _Deployment extends DeploymentWithDefinitions {
  @Override
  String getId();

  @Override
  String getName();

  @Override
  default Date getDeploymentTime() {
    return ClockUtil.getCurrentTime();
  }

  @Override
  String getSource();

  @Override
  @Nullable
  String getTenantId();

  @Override
  default List<ProcessDefinition> getDeployedProcessDefinitions() {
    return Collections.emptyList();
  }

  @Override
  default List<CaseDefinition> getDeployedCaseDefinitions() {
    return Collections.emptyList();
  }

  @Override
  default List<DecisionDefinition> getDeployedDecisionDefinitions() {
    return Collections.emptyList();
  }

  @Override
  default List<DecisionRequirementsDefinition> getDeployedDecisionRequirementsDefinitions() {
    return Collections.emptyList();
  }
}
