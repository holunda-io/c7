package io.holunda.commons.immutables.runtime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#eventSubscription(EventSubscription)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableEventSubscription.class)
@JsonSerialize(as = ImmutableEventSubscription.class)
@SuppressWarnings("java:S114")
interface _EventSubscription extends EventSubscription {

  @Override
  String getId();

  @Override
  String getEventType();

  @Override
  String getEventName();

  @Override
  String getExecutionId();

  @Override
  String getProcessInstanceId();

  @Override
  String getActivityId();

  @Nullable
  @Override
  String getTenantId();

  @Override
  default Date getCreated() {
    return ClockUtil.getCurrentTime();
  }
}
