package io.holunda.commons.immutables.history;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.impl.history.event.HistoricDecisionEvaluationEvent;
import org.camunda.bpm.engine.impl.history.event.HistoricDecisionInstanceEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEventType;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableHistoricDecisionEvaluationEvent.class)
@JsonSerialize(as = ImmutableHistoricDecisionEvaluationEvent.class)
@SuppressWarnings("java:S114")
abstract class _HistoricDecisionEvaluationEvent extends _HistoricDecisionEvaluationEventWithoutSetters {

  @Nullable
  @Override
  public abstract HistoricDecisionInstanceEntity getRootHistoricDecisionInstance();

  @Override
  @Value.Default
  public Collection<HistoricDecisionInstanceEntity> getRequiredHistoricDecisionInstances() {
    return Collections.emptyList();
  }

  @Nullable
  @Override
  public abstract String getProcessInstanceId();

  @Nullable
  @Override
  public abstract String getRootProcessInstanceId();

  @Nullable
  @Override
  public abstract String getExecutionId();

  @Override
  @Nullable
  public abstract String getProcessDefinitionId();

  @Override
  @Nullable
  public abstract String getProcessDefinitionKey();

  @Override
  @Nullable
  public abstract String getProcessDefinitionName();

  @Override
  @Nullable
  public abstract Integer getProcessDefinitionVersion();

  @Override
  @Nullable
  public abstract String getCaseDefinitionName();

  @Override
  @Nullable
  public abstract String getCaseDefinitionKey();

  @Override
  @Nullable
  public abstract String getCaseDefinitionId();

  @Override
  @Nullable
  public abstract String getCaseInstanceId();

  @Override
  @Nullable
  public abstract String getCaseExecutionId();

  @Override
  public abstract String getId();

  @Override
  @Value.Derived
  public String getEventType() {
    return HistoryEventTypes.DMN_DECISION_EVALUATE.getEventName();
  }

  @Override
  @Value.Default
  public long getSequenceCounter() {
    return 0L;
  }

  @Override
  @Nullable
  public abstract Date getRemovalTime();

  @Override
  @Nullable
  public abstract Object getPersistentState();

  @Override
  public boolean isEventOfType(HistoryEventType type) {
    return type.getEventName().equals(getEventType());
  }

  @Override
  public String toString() {
    return super.toString();
  }
}

@SuppressWarnings({"java:S114", "java:S1133"})
abstract class _HistoricDecisionEvaluationEventWithoutSetters extends HistoricDecisionEvaluationEvent {

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setRootHistoricDecisionInstance(HistoricDecisionInstanceEntity rootHistoricDecisionInstance) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setRequiredHistoricDecisionInstances(Collection<HistoricDecisionInstanceEntity> requiredHistoricDecisionInstances) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setProcessInstanceId(String processInstanceId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setRootProcessInstanceId(String rootProcessInstanceId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setExecutionId(String executionId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setProcessDefinitionId(String processDefinitionId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setProcessDefinitionKey(String processDefinitionKey) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setProcessDefinitionName(String processDefinitionName) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setCaseDefinitionName(String caseDefinitionName) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setCaseDefinitionKey(String caseDefinitionKey) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setCaseDefinitionId(String caseDefinitionId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setCaseInstanceId(String caseInstanceId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setCaseExecutionId(String caseExecutionId) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setId(String id) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setEventType(String eventType) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setSequenceCounter(long sequenceCounter) {
    throw UNMODIFIABLE;
  }

  /**
   * @deprecated setter has to be implemented to fulfill the interface but will throw {@link UnsupportedOperationException}.
   */
  @Override
  @Deprecated(since = "0.14.0")
  public void setRemovalTime(Date removalTime) {
    throw UNMODIFIABLE;
  }
}
