package org.camunda.community.mockito;

import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessInstanceWithVariablesImpl;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.JobQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.community.mockito.function.DeployProcess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.Instant;
import java.util.*;

import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.bpm.model.xml.test.assertions.ModelAssertions.assertThat;
import static org.camunda.community.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;
import static org.camunda.community.mockito.ProcessExpressions.registerCallActivityMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CallActivityMockExampleTest {

  private static final String PROCESS_ID = "myProcess";
  private static final String SUB_PROCESS_ID = "mySubProcess";
  private static final String SUB_PROCESS2_ID = "mySubProcess2";
  private static final String MESSAGE_DOIT = "DOIT";
  private static final String SIGNAL_ALLDOIT = "ALLDOIT";
  private static final String TASK_USERTASK = "user_task";

  @RegisterExtension
  public static final ProcessEngineExtension camunda = ProcessEngineExtension.builder()
    .useProcessEngine(mostUsefulProcessEngineConfiguration().buildProcessEngine())
    .build();

  private final List<String> deploymentIds = new ArrayList<>();

  private void manageDeployment(Deployment deployment) {
    if (deployment != null) {
      deploymentIds.add(deployment.getId());
    }
  }

  @AfterEach
  void tearDown() {
    final var repositoryService = camunda.getProcessEngine().getRepositoryService();
    for (String id : deploymentIds) {
      try {
        repositoryService.deleteDeployment(id, true);
      } catch (Exception ignored) {
      }
    }
    deploymentIds.clear();
  }

  @BeforeEach
  public void setUp() {
    prepareProcessWithOneSubprocess();
  }

  @Test
  public void register_subprocess_mock_addVar() {
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionAddVariable("foo", "bar")
      .deploy(camunda.getProcessEngine()));

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    isWaitingAt(processInstance, TASK_USERTASK);

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = camunda.getProcessEngine().getRuntimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "bar");
  }

  @Test
  public void register_subprocess_mock_withOwnConsumer() {
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionDo(execution -> {
        execution.setVariable("foo", "barbar");
      })
      .deploy(camunda.getProcessEngine()));

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    isWaitingAt(processInstance, TASK_USERTASK);

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = camunda.getProcessEngine().getRuntimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "barbar");
  }

  @Test
  public void register_subprocess_mock_withReceiveMessage() {
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForMessage(MESSAGE_DOIT)
      .deploy(camunda.getProcessEngine()));

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThatProcessIsWaitingForMessage(MESSAGE_DOIT);

    camunda.getProcessEngine().getRuntimeService().correlateMessage(MESSAGE_DOIT);
    isWaitingAt(processInstance, TASK_USERTASK);
  }

  @Test
  public void register_subprocess_mock_withReceiveSignal() {
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForSignal(SIGNAL_ALLDOIT)
      .deploy(camunda.getProcessEngine()));

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThatProcessIsWaitingForSignal(SIGNAL_ALLDOIT);

    camunda.getProcessEngine().getRuntimeService().createSignalEvent(SIGNAL_ALLDOIT).send();
    isWaitingAt(processInstance, TASK_USERTASK);
  }

  @Test
  public void register_subprocess_mock_withSendMessage() {
    manageDeployment(
      registerCallActivityMock(SUB_PROCESS_ID)
        .onExecutionSendMessage(MESSAGE_DOIT)
        .deploy(camunda.getProcessEngine())
    );

    final String waitForMessageId = "waitForMessage";
    final BpmnModelInstance waitForMessage = Bpmn.createExecutableProcess(waitForMessageId)
      .camundaHistoryTimeToLive(1)
      .startEvent("start")
      .intermediateCatchEvent("waitForMessageCatchEvent")
      .message(MESSAGE_DOIT)
      .endEvent("end")
      .done();

    manageDeployment(new DeployProcess(camunda.getProcessEngine()).apply(waitForMessageId, waitForMessage));

    //Start monitoring process for testing
    final ProcessInstance waitingProcessInstance = startProcess(waitForMessageId);
    assertThatProcessIsWaitingForMessage(MESSAGE_DOIT);

    //Start our process with mocked subprocess
    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    isWaitingAt(processInstance, TASK_USERTASK);
    //Our monitoring process should be finished
    isEnded(waitingProcessInstance);
  }

  @Test
  public void register_subprocess_mock_withTimerDate() {
    final Date date = Date.from(Instant.now().plusSeconds(60));

    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForTimerWithDate(date)
      .deploy(camunda.getProcessEngine()));

    startProcess(PROCESS_ID);

    assertThatTimerIsWaitingUntil(date);
  }

  @Test
  public void register_subprocess_mock_withTimerDuration() {
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForTimerWithDuration("PT60S")
      .deploy(camunda.getProcessEngine()));

    startProcess(PROCESS_ID);

    assertThatTimerIsWaitingUntil(Date.from(Instant.now().plusSeconds(60)));
  }

  @Test
  public void register_subprocess_mock_withException() {
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionRunIntoError(new Exception("No"))
      .deploy(camunda.getProcessEngine()));

    assertThrows(RuntimeException.class, () -> startProcess(PROCESS_ID));
  }

  @Test
  public void register_subprocesses_mocks_withVariables() {
    final BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .camundaHistoryTimeToLive(1)
      .startEvent("start")
      .callActivity("call_subprocess")
      .camundaOut("foo", "foo")
      .calledElement(SUB_PROCESS_ID)
      .callActivity("call_subprocess2")
      .calledElement(SUB_PROCESS2_ID)
      .camundaOut("bar", "bar")
      .userTask(TASK_USERTASK)
      .endEvent("end")
      .done();

    manageDeployment(new DeployProcess(camunda.getProcessEngine()).apply(PROCESS_ID, processWithSubProcess));

    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
      .deploy(camunda.getProcessEngine()));
    manageDeployment(registerCallActivityMock(SUB_PROCESS2_ID)
      .onExecutionSetVariables(createVariables().putValue("bar", "foo"))
      .deploy(camunda.getProcessEngine()));

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    isWaitingAt(processInstance, TASK_USERTASK);

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = camunda.getProcessEngine().getRuntimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(2);
    assertThat(variables).containsEntry("foo", "bar");
    assertThat(variables).containsEntry("bar", "foo");
  }

  @Test
  public void register_subprocesses_mocks_withWaitMessage_and_timer_and_setVariable() {
    prepareProcessWithOneSubprocess();

    final Date waitUntil = Date.from(Instant.now().plusSeconds(60));
    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForMessage(MESSAGE_DOIT)
      .onExecutionWaitForTimerWithDate(waitUntil)
      .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
      .deploy(camunda.getProcessEngine()));

    final ProcessInstance processInstance = startProcess(PROCESS_ID);

    //Message should wait for message
    assertThatProcessIsWaitingForMessage(MESSAGE_DOIT);
    camunda.getProcessEngine().getRuntimeService().correlateMessage(MESSAGE_DOIT);

    //Message should wait for date
    Job job = assertThatTimerIsWaitingUntil(waitUntil);
    execute(job);

    //Process should wait at user task
    isWaitingAt(processInstance, TASK_USERTASK);

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = camunda.getProcessEngine().getRuntimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "bar");
  }

  @Test
  public void register_subprocess_mock_throwEscalation() {
    String escalationCode = "SOME_ERROR";
    String subprocessId = "call_subprocess";
    String escalationEndId = "EscalationEnd";

    BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .camundaHistoryTimeToLive(1)
      .startEvent("start")
      .callActivity(subprocessId)
      .calledElement(SUB_PROCESS_ID)
      .boundaryEvent()
      .escalation(escalationCode)
      .endEvent(escalationEndId)
      .moveToActivity(subprocessId)
      .userTask(TASK_USERTASK)
      .endEvent("end")
      .done();

    manageDeployment(new DeployProcess(camunda.getProcessEngine()).apply(PROCESS_ID, processWithSubProcess));

    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionThrowEscalation(escalationCode)
      .deploy(camunda.getProcessEngine()));

    ProcessInstance processInstance = startProcess(PROCESS_ID);

    isEnded(processInstance);
    assertEquals(escalationEndId, ((ProcessInstanceWithVariablesImpl) processInstance).getExecutionEntity().getActivityId());
  }

  @Test
  public void register_subprocess_mock_throwError() {
    String errorCode = "SOME_ERROR";
    String subprocessId = "call_subprocess";
    String errorEndId = "ErrorEnd";

    BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .camundaHistoryTimeToLive(1)
      .startEvent("start")
      .callActivity(subprocessId)
      .calledElement(SUB_PROCESS_ID)
      .boundaryEvent()
      .error(errorCode)
      .endEvent(errorEndId)
      .moveToActivity(subprocessId)
      .userTask(TASK_USERTASK)
      .endEvent("end")
      .done();

    manageDeployment(new DeployProcess(camunda.getProcessEngine()).apply(PROCESS_ID, processWithSubProcess));

    manageDeployment(registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionThrowError(errorCode)
      .deploy(camunda.getProcessEngine()));

    ProcessInstance processInstance = startProcess(PROCESS_ID);

    isEnded(processInstance);
    assertEquals(errorEndId, ((ProcessInstanceWithVariablesImpl) processInstance).getExecutionEntity().getActivityId());
  }

  private void prepareProcessWithOneSubprocess() {
    final BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .camundaHistoryTimeToLive(1)
      .startEvent("start")
      .callActivity("call_subprocess")
      .camundaOut("foo", "foo")
      .calledElement(SUB_PROCESS_ID)
      .userTask(TASK_USERTASK)
      .endEvent("end")
      .done();

    manageDeployment(new DeployProcess(camunda.getProcessEngine()).apply(PROCESS_ID, processWithSubProcess));
  }

  private void assertThatProcessIsWaitingForMessage(String message) {
    final EventSubscription eventSubscription = camunda.getProcessEngine().getRuntimeService().createEventSubscriptionQuery().singleResult();
    assertThat(eventSubscription).isNotNull();
    assertThat(eventSubscription.getEventName()).isEqualTo(message);
  }

  private void assertThatProcessIsWaitingForSignal(String signalName) {
    final EventSubscription eventSubscription = camunda.getProcessEngine().getRuntimeService().createEventSubscriptionQuery().singleResult();
    assertThat(eventSubscription).isNotNull();
    assertThat(eventSubscription.getEventName()).isEqualTo(signalName);
  }

  private Job assertThatTimerIsWaitingUntil(Date date) {
    final List<Job> list = jobQuery().list();
    assertThat(list).hasSize(1);
    final Job timer = list.get(0);
    assertThat(timer).isInstanceOf(TimerEntity.class);
    assertThat(timer.getDuedate()).isInSameSecondWindowAs(date); // Check the time distance (second boundary does not matter)
    return timer;
  }

  private ProcessInstance startProcess(final String key) {
    return camunda.getProcessEngine().getRuntimeService().startProcessInstanceByKey(key);
  }


  private void isWaitingAt(ProcessInstance processInstance, String activityId) {
    assertThat(camunda.getProcessEngine().getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstance.getId()).activityIdIn(activityId).active().singleResult()).isNotNull();
  }

  private void isEnded(ProcessInstance processInstance) {
    Optional<HistoricProcessInstance> hi = Optional.ofNullable(camunda.getProcessEngine().getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult())
      .filter(h -> h.getEndTime() != null);

    assertThat(hi).as("instance not ended").isNotEmpty();
  }

  private JobQuery jobQuery() {
    return camunda.getProcessEngine().getManagementService().createJobQuery().active();
  }

  private void execute(Job job) {
    camunda.getProcessEngine().getManagementService().executeJob(job.getId());
  }
}
