package org.camunda.community.mockito;

import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.community.mockito.process.CallActivityMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.community.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;

public class CallActivityMockBindingDeploymentTest {

  public static final String KEY = "process_with_callActivity_binding_deployment";
  public static final String KEY_MOCK = "do_stuff_mock";

  @RegisterExtension
  public static final ProcessEngineExtension camunda = ProcessEngineExtension.builder()
    .useProcessEngine(mostUsefulProcessEngineConfiguration().buildProcessEngine())
    .build();

  private Deployment deployment;

  @BeforeEach
  public void setUp() {
    DeploymentBuilder deploymentBuilder = camunda.getProcessEngine().getRepositoryService().createDeployment();
    deploymentBuilder.addClasspathResource("process_with_callActivity_binding_deployment.bpmn");
    CallActivityMock mock = CamundaMockito.registerCallActivityMock(KEY_MOCK).onExecutionAddVariable("foo", "bar");
    mock.addToDeployment(deploymentBuilder);
    deployment = deploymentBuilder.deploy();
  }

  @AfterEach
  void tearDown() {
    if (deployment != null) {
      camunda.getProcessEngine().getRepositoryService().deleteDeployment(deployment.getId(), true);
    }
  }

  @Test
  public void mock_runs_with_binding_deployment() {
    ProcessInstance processInstance = camunda.getProcessEngine().getRuntimeService().startProcessInstanceByKey(KEY);

    // instance waits in endEvent
    ProcessInstance found = camunda.getProcessEngine().getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstance.getId())
      .activityIdIn("endEvent")
      .singleResult();
    assertThat(found).isNotNull();

    // subProcess set variable foo
    assertThat(camunda.getProcessEngine().getRuntimeService().getVariable(found.getProcessInstanceId(), "foo")).isEqualTo("bar");
  }
}
