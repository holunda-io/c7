package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.DATE_NOW;
import static io.holunda.commons.immutables._Fixtures.DATE_TOMORROW;
import static io.holunda.commons.immutables._Fixtures.JOB;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTest extends _BasicImmutableTest<ImmutableJob> {

  public JobTest() {
    super(ImmutableJob.class);
  }

  @Override
  public void factory_method() {
    ImmutableJob job = createDto();

    assertThat(job).hasId(JOB.getId());
    assertThat(job).hasProcessInstanceId(JOB.getProcessInstanceId());
    assertThat(job).hasExecutionId(JOB.getExecutionId());
    assertThat(job).hasProcessDefinitionId(JOB.getProcessDefinitionId());
    assertThat(job).hasDeploymentId(JOB.getDeploymentId());
    assertThat(job).hasProcessDefinitionKey(JOB.getProcessDefinitionKey());
    assertThat(job).hasJobDefinitionId(JOB.getJobDefinitionId());
    assertThat(job).hasCreateTime(DATE_NOW);
    assertThat(job).hasDuedate(DATE_TOMORROW);
    assertThat(job).hasRetries(JOB.getRetries());
    assertThat(job).hasExceptionMessage(JOB.getExceptionMessage());
    assertThat(job).isSuspended();
    assertThat(job).hasPriority(JOB.getPriority());
    assertThat(job).hasTenantId(TENANT_ID);

  }

  @Override
  public void create_minimal_dto() {

    ImmutableJob job = ImmutableJob.builder()
      .id(uuid())
      .processInstanceId(uuid())
      .processDefinitionId(uuid())
      .processDefinitionKey("process")
      .executionId(uuid())
      .deploymentId(uuid())
      .jobDefinitionId(uuid())
      .priority(50)
      .build();

    assertThat(job.getId()).isNotBlank();
  }

  @Override
  ImmutableJob createDto() {
    return CamundaImmutables.job(JOB);
  }
}
