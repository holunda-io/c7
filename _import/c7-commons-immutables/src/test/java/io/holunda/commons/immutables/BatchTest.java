package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutablesTest.uuid;
import static org.assertj.core.api.Assertions.assertThat;

import io.holunda.commons.immutables.CamundaImmutablesTest.BasicImmutableTest;
import io.holunda.commons.immutables.JacksonHelper.JsonMapper;
import org.junit.Test;

public class BatchTest implements BasicImmutableTest {

  private final JsonMapper<ImmutableBatch> mapper = JacksonHelper.jsonMapper(ImmutableBatch.class);

  @Test
  @Override
  public void create_minimal_dto() {
    ImmutableBatch dto = ImmutableBatch.builder()
      .id(uuid())
      .type("type")
      .totalJobs(10)
      .jobsCreated(0)
      .batchJobsPerSeed(1)
      .invocationsPerBatchJob(1)
      .seedJobDefinitionId("jobDefinition")
      .monitorJobDefinitionId("monitorJob")
      .batchJobDefinitionId("batchJobDefinition")
      .build();
  }

  @Test
  @Override
  public void convert_json() {
    ImmutableBatch dto = ImmutableBatch.builder()
      .id(uuid())
      .type("type")
      .totalJobs(10)
      .jobsCreated(0)
      .batchJobsPerSeed(1)
      .invocationsPerBatchJob(1)
      .seedJobDefinitionId("jobDefinition")
      .monitorJobDefinitionId("monitorJob")
      .batchJobDefinitionId("batchJobDefinition")
      .build();

    String json = mapper.toJson(dto);

    assertThat(mapper.fromJson(json)).isEqualTo(dto);
  }
}
