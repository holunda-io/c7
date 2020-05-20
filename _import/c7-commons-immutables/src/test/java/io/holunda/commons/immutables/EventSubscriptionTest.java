package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.ACTIVITY_ID;
import static io.holunda.commons.immutables._Fixtures.DATE_NOW;
import static io.holunda.commons.immutables._Fixtures.EVENT_SUBSCRIPTION;
import static io.holunda.commons.immutables._Fixtures.EXECUTION_ID;
import static io.holunda.commons.immutables._Fixtures.PROCESS_INSTANCE_ID;
import static io.holunda.commons.immutables._Fixtures.TENANT_ID;
import static io.holunda.commons.immutables._Fixtures.uuid;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import org.camunda.bpm.engine.impl.event.EventType;
import org.junit.Test;

public class EventSubscriptionTest extends _BasicImmutableTest<ImmutableEventSubscription> {

  public EventSubscriptionTest() {
    super(ImmutableEventSubscription.class);
  }

  @Override
  public void factory_method() {
    ImmutableEventSubscription dto = createDto();

    assertThat(dto).hasId(EVENT_SUBSCRIPTION.getId());
    assertThat(dto).hasEventType(EVENT_SUBSCRIPTION.getEventType());
    assertThat(dto).hasEventName(EVENT_SUBSCRIPTION.getEventName());
    assertThat(dto).hasExecutionId(EXECUTION_ID);
    assertThat(dto).hasProcessInstanceId(PROCESS_INSTANCE_ID);
    assertThat(dto).hasActivityId(ACTIVITY_ID);
    assertThat(dto).hasTenantId(TENANT_ID);
    assertThat(dto).hasCreated(DATE_NOW);
  }

  @Test
  @Override
  public void create_minimal_dto() {
    ImmutableEventSubscription.builder()
      .id(uuid())
      .eventType("xxx")
      .eventName("event")
      .executionId(uuid())
      .processInstanceId(uuid())
      .activityId("activity")
      .build();
  }


  @Override
  ImmutableEventSubscription createDto() {
    return CamundaImmutables.eventSubscription(_Fixtures.EVENT_SUBSCRIPTION);
  }
}
