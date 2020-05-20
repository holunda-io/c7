package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.uuid;

import org.junit.Ignore;

@Ignore
public class BatchTest extends _BasicImmutableTest<ImmutableBatch> {

  public BatchTest() {
    super(ImmutableBatch.class);
  }


  @Override
  public void factory_method() {
    ImmutableBatch dto = createDto();

    assertThat(dto).isSuspended();
    assertThat(dto).hasCreateUserId(_Fixtures.BATCH.getCreateUserId());
    assertThat(dto).hasId(_Fixtures.BATCH.getId());
    assertThat(dto).hasBatchJobDefinitionId(_Fixtures.BATCH.getBatchJobDefinitionId());
    assertThat(dto).hasBatchJobsPerSeed(_Fixtures.BATCH.getBatchJobsPerSeed());
    assertThat(dto).hasInvocationsPerBatchJob(_Fixtures.BATCH.getInvocationsPerBatchJob());
    assertThat(dto).hasJobsCreated(_Fixtures.BATCH.getJobsCreated());
    assertThat(dto).hasTotalJobs(_Fixtures.BATCH.getTotalJobs());
    assertThat(dto).hasMonitorJobDefinitionId(_Fixtures.BATCH.getMonitorJobDefinitionId());

  }

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


  @Override
  ImmutableBatch createDto() {
    return CamundaImmutables.batch(_Fixtures.BATCH);
  }
}
