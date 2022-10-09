package io.holunda.commons.immutables.runtime;

import io.holunda.commons.immutables.CamundaImmutables;
import io.holunda.commons.immutables._BasicImmutableTest;
import org.camunda.bpm.engine.runtime.Incident;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.ACTIVITY_ID;
import static io.holunda.commons.immutables._Fixtures.DATE_NOW;
import static io.holunda.commons.immutables._Fixtures.EXECUTION_ID;
import static io.holunda.commons.immutables._Fixtures.INCIDENT;
import static io.holunda.commons.immutables._Fixtures.PROCESS_DEFINITION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;

@SuppressWarnings({"java:S2699","java:S2187"})
public class IncidentTest extends _BasicImmutableTest<ImmutableIncident> {

  public IncidentTest() {
    super(ImmutableIncident.class);
  }

  @Override
  public void factory_method() {
    final ImmutableIncident dto = createDto();

    assertThat(dto).hasId(INCIDENT.getId());
    assertThat(dto).hasIncidentTimestamp(DATE_NOW);
    assertThat(dto).hasIncidentType(Incident.FAILED_JOB_HANDLER_TYPE);
    assertThat(dto).hasIncidentMessage(INCIDENT.getIncidentMessage());
    assertThat(dto).hasExecutionId(EXECUTION_ID);
    assertThat(dto).hasActivityId(ACTIVITY_ID);
    assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    assertThat(dto).hasProcessDefinitionId(PROCESS_DEFINITION_ID);
    assertThat(dto).hasCauseIncidentId(INCIDENT.getCauseIncidentId());
    assertThat(dto).hasRootCauseIncidentId(INCIDENT.getRootCauseIncidentId());
    assertThat(dto).hasConfiguration(INCIDENT.getConfiguration());
    assertThat(dto).hasTenantId(TENANT_ID);
    assertThat(dto).hasJobDefinitionId(INCIDENT.getJobDefinitionId());
    assertThat(dto).hasHistoryConfiguration(INCIDENT.getHistoryConfiguration());
  }

  @Override
  public void create_minimal_dto() {
    ImmutableIncident.builder()
      .id(uuid())
      .incidentType(Incident.FAILED_JOB_HANDLER_TYPE)
      .jobDefinitionId("jobDefId")
      .executionId(EXECUTION_ID)
      .processInstanceId(PROCESS_INSTANCE_ID)
      .processDefinitionId(PROCESS_DEFINITION_ID)
      .configuration("configuration")
      .build();
  }

  @Override
  protected ImmutableIncident createDto() {
    return CamundaImmutables.incident(INCIDENT);
  }
}
