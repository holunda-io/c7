package org.camunda.community.mockito;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.community.mockito.DelegateExpressions.*;
import static org.camunda.community.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;

/**
 * If everything works as expected, the process can be deployed and executed
 * without explicitly registering mocks for the delegate, the execution- and the
 * task-listener.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class AutoMockProcessTest {

  @RegisterExtension
  public static final ProcessEngineExtension processEngineRule = ProcessEngineExtension.builder()
    .useProcessEngine(mostUsefulProcessEngineConfiguration().buildProcessEngine())
    .build();

  private TaskService taskService;

  @BeforeEach
  public void setUp() {
    taskService = processEngineRule.getProcessEngine().getTaskService();
  }

  @Test
  @Deployment(resources = "MockProcess.bpmn")
  public void register_mocks_for_all_listeners_and_delegates() {
    autoMock("MockProcess.bpmn");

    assertThat(Mocks.get("loadData")).isNotNull();
    assertThat(Mocks.get("saveData")).isNotNull();
    assertThat(Mocks.get("report")).isNotNull();

    final ProcessInstance processInstance = processEngineRule.getProcessEngine().getRuntimeService().startProcessInstanceByKey("process_mock_dummy");

    assertThat(processEngineRule.getProcessEngine().getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult()).isNotNull();

    completeTask();

    verifyMocks();
  }

  @Test
  @Deployment(resources = "MockProcess_withoutNS.bpmn")
  public void register_mocks_for_all_listeners_and_delegates_noNS() {
    autoMock("MockProcess_withoutNS.bpmn");

    assertThat(Mocks.get("loadData")).isNotNull();
    assertThat(Mocks.get("saveData")).isNotNull();

    final ProcessInstance processInstance = processEngineRule.getProcessEngine().getRuntimeService().startProcessInstanceByKey("process_mock_dummy");

    assertThat(processEngineRule.getProcessEngine().getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult()).isNotNull();

    completeTask();

    verifyMocks();
  }

  private void verifyMocks() {
    verifyTaskListenerMock("verifyData").executed();
    verifyExecutionListenerMock("startProcess").executed();
    verifyJavaDelegateMock("loadData").executed();
    verifyJavaDelegateMock("saveData").executed();
    verifyJavaDelegateMock("report").executed();
    verifyExecutionListenerMock("beforeLoadData").executed();
  }

  private void completeTask() {
    taskService.complete(taskService.createTaskQuery().singleResult().getId());
  }
}
