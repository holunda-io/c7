package io.holunda.commons.immutables.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.CurrentTimestamp;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.task.Comment;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Value.Immutable
@CamundaPojoStyle
@JsonSerialize(as = ImmutableComment.class)
@JsonDeserialize(as = ImmutableComment.class)
@SuppressWarnings("java:S114")
interface _Comment extends Comment, CurrentTimestamp {

  @Override
  @Nullable
  String getUserId();

  @Override
  default Date getTime() {
    return getNow().get();
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
