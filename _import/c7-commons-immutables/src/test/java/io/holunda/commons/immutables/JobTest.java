package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutablesTest.uuid;
import static io.holunda.commons.immutables.JacksonHelper.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

import io.holunda.commons.immutables.CamundaImmutablesTest.BasicImmutableTest;
import io.holunda.commons.immutables.JacksonHelper.JsonMapper;
import org.junit.Test;

public class JobTest implements BasicImmutableTest {

  private final JsonMapper<ImmutableJob> mapper = jsonMapper(ImmutableJob.class);


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

  @Test
  @Override
  public void convert_json() {
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

    String json = mapper.toJson(job);

    assertThat(mapper.fromJson(json)).isEqualTo(job);
  }
}
