package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.uuid;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JobTest extends _BasicImmutableTest<ImmutableJob> {

  public JobTest() {
    super(ImmutableJob.class);
  }


  @Test
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
    return CamundaImmutables.job(null);
  }
}
