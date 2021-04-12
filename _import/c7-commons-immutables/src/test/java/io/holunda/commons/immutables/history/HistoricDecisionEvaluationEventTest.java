package io.holunda.commons.immutables.history;

import io.holunda.commons.immutables.CamundaImmutables;
import io.holunda.commons.immutables._BasicImmutableTest;
import io.holunda.commons.immutables._Fixtures;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.impl.history.event.HistoricDecisionEvaluationEvent;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;
import org.junit.Test;

import static io.holunda.commons.immutables._Fixtures.uuid;
import static io.holunda.commons.immutables.history.ImmutableHistoricDecisionEvaluationEventAssert.assertThat;

@SuppressWarnings({"java:S2699", "java:S2187"})
public class HistoricDecisionEvaluationEventTest extends _BasicImmutableTest<ImmutableHistoricDecisionEvaluationEvent> {

  public HistoricDecisionEvaluationEventTest() {
    super(ImmutableHistoricDecisionEvaluationEvent.class);
  }

  @Override
  public void factory_method() {
    var dto = createDto();

    assertThat(dto).hasId(_Fixtures.ID);
    assertThat(dto).hasEventType(HistoryEventTypes.DMN_DECISION_EVALUATE.getEventName());
    Assertions.assertThat(dto.isEventOfType(HistoryEventTypes.DMN_DECISION_EVALUATE)).isTrue();

    assertThat(dto).hasCaseDefinitionId(_Fixtures.CASE_DEFINITION_ID);
    assertThat(dto).hasCaseDefinitionKey(_Fixtures.CASE_DEFINITION_KEY);
    assertThat(dto).hasCaseDefinitionName(_Fixtures.CASE_DEFINITION_NAME);
    assertThat(dto).hasCaseExecutionId(_Fixtures.CASE_EXECUTION_ID);
    assertThat(dto).hasCaseInstanceId(_Fixtures.CASE_INSTANCE_ID);

    assertThat(dto).hasProcessDefinitionId(_Fixtures.PROCESS_DEFINITION_ID);
    assertThat(dto).hasProcessDefinitionKey(_Fixtures.PROCESS_DEFINITION_KEY);
    assertThat(dto).hasProcessDefinitionName(_Fixtures.PROCESS_DEFINITION_NAME);
    assertThat(dto).hasProcessDefinitionVersion(_Fixtures.PROCESS_DEFINITION_VERSION);
    assertThat(dto).hasRootProcessInstanceId(_Fixtures.ROOT_PROCESS_INSTANCE_ID);

    log.info(mapper.toJson(dto));
  }

  @Override
  public void create_minimal_dto() {
    ImmutableHistoricDecisionEvaluationEvent.builder()
      .id(uuid())
      .build();
  }

  @Override
  protected ImmutableHistoricDecisionEvaluationEvent createDto() {
    return ImmutableHistoricDecisionEvaluationEvent.builder()
      .id(_Fixtures.ID)

      .caseDefinitionId(_Fixtures.CASE_DEFINITION_ID)
      .caseDefinitionKey(_Fixtures.CASE_DEFINITION_KEY)
      .caseDefinitionName(_Fixtures.CASE_DEFINITION_NAME)
      .caseExecutionId(_Fixtures.CASE_EXECUTION_ID)
      .caseInstanceId(_Fixtures.CASE_INSTANCE_ID)

      .processDefinitionId(_Fixtures.PROCESS_DEFINITION_ID)
      .processDefinitionKey(_Fixtures.PROCESS_DEFINITION_KEY)
      .processDefinitionName(_Fixtures.PROCESS_DEFINITION_NAME)
      .processDefinitionVersion(_Fixtures.PROCESS_DEFINITION_VERSION)
      .processInstanceId(_Fixtures.PROCESS_INSTANCE_ID)
      .rootProcessInstanceId(_Fixtures.ROOT_PROCESS_INSTANCE_ID)

      .executionId(_Fixtures.EXECUTION_ID)
      .removalTime(_Fixtures.DATE_TOMORROW)

      .build();
  }

  @Test
  public void from_entity() {
    var evt = new HistoricDecisionEvaluationEvent();
    evt.setId("1");
    evt.setSequenceCounter(11L);

    var dto = CamundaImmutables.historicDecisionEvaluationEvent(evt);

    assertThat(dto).hasId("1");
    assertThat(dto).hasSequenceCounter(11L);
  }
}
