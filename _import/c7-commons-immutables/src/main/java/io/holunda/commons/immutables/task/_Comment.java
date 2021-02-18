package io.holunda.commons.immutables.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.task.Comment;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#comment(Comment)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonSerialize(as = ImmutableComment.class)
@JsonDeserialize(as = ImmutableComment.class)
@SuppressWarnings("java:S114")
interface _Comment extends Comment{

  @Override
  String getId();

  @Override
  @Nullable
  String getUserId();

  @Override
  default Date getTime() {
    return ClockUtil.getCurrentTime();
  }

  @Override
  String getTaskId();

  @Override
  String getProcessInstanceId();

  @Override
  @Nullable
  String getRootProcessInstanceId();

  @Override
  @Nullable
  String getFullMessage();

  @Override
  @Nullable
  Date getRemovalTime();
}
