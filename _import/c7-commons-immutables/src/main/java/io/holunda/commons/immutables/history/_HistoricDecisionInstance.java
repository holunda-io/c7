package io.holunda.commons.immutables.history;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.history.HistoricDecisionInputInstance;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;
import org.immutables.value.Value;

import java.util.Date;
import java.util.List;

@Value.Immutable
@CamundaPojoStyle
//@JsonDeserialize(as = ImmutableHistoricDecisionEvaluationEvent.class)
//@JsonSerialize(as = ImmutableHistoricDecisionEvaluationEvent.class)
@SuppressWarnings("java:S114")
interface _HistoricDecisionInstance extends HistoricDecisionInstance {

//  @Override
//  default String getId() {
//    return null;
//  }
//
//  @Override
//  default String getDecisionDefinitionId() {
//    return null;
//  }
//
//  @Override
//  default String getDecisionDefinitionKey() {
//    return null;
//  }
//
//  @Override
//  default String getDecisionDefinitionName() {
//    return null;
//  }
//
//  @Override
//  default Date getEvaluationTime() {
//    return null;
//  }
//
//  @Override
//  default Date getRemovalTime() {
//    return null;
//  }
//
//  @Override
//  default String getProcessDefinitionKey() {
//    return null;
//  }
//
//  @Override
//  default String getProcessDefinitionId() {
//    return null;
//  }
//
//  @Override
//  default String getProcessInstanceId() {
//    return null;
//  }
//
//  @Override
//  default String getCaseDefinitionKey() {
//    return null;
//  }
//
//  @Override
//  default String getCaseDefinitionId() {
//    return null;
//  }
//
//  @Override
//  default String getCaseInstanceId() {
//    return null;
//  }
//
//  @Override
//  default String getActivityId() {
//    return null;
//  }
//
//  @Override
//  default String getActivityInstanceId() {
//    return null;
//  }
//
//  @Override
//  default String getUserId() {
//    return null;
//  }
//
//  @Override
//  default List<HistoricDecisionInputInstance> getInputs() {
//    return null;
//  }
//
//  @Override
//  default List<HistoricDecisionOutputInstance> getOutputs() {
//    return null;
//  }
//
//  @Override
//  default Double getCollectResultValue() {
//    return null;
//  }
//
//  @Override
//  default String getRootDecisionInstanceId() {
//    return null;
//  }
//
//  @Override
//  default String getRootProcessInstanceId() {
//    return null;
//  }
//
//  @Override
//  default String getDecisionRequirementsDefinitionId() {
//    return null;
//  }
//
//  @Override
//  default String getDecisionRequirementsDefinitionKey() {
//    return null;
//  }
//
//  @Override
//  default String getTenantId() {
//    return null;
//  }
}
