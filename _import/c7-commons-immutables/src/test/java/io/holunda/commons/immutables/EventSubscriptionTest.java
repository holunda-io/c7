package io.holunda.commons.immutables;

import static io.holunda.commons.immutables._Fixtures.uuid;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class EventSubscriptionTest extends _BasicImmutableTest<ImmutableEventSubscription> {

  public EventSubscriptionTest() {
    super(ImmutableEventSubscription.class);
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

  @Test
  @Override
  public void convert_json() {
    ImmutableEventSubscription dto = ImmutableEventSubscription.builder()
      .id(uuid())
      .eventType("xxx")
      .eventName("event")
      .executionId(uuid())
      .processInstanceId(uuid())
      .activityId("activity")
      .created(new Date())
      .build();

    String json = mapper.toJson(dto);
    assertThat(mapper.fromJson(json)).isEqualTo(dto);
  }

  @Override
  ImmutableEventSubscription createDto() {
    return CamundaImmutables.eventSubscription(null);
  }
}
