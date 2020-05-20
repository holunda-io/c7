package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CurrentTimestamp;
import java.util.Date;
import org.camunda.bpm.engine.task.Comment;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonSerialize(as = ImmutableComment.class)
@JsonDeserialize(as = ImmutableComment.class)
public interface _Comment extends Comment, CurrentTimestamp {

  @Override
  @Nullable
  String getUserId();

  @Override
  default Date getTime() {
    return getNow().get();
  }

  @Override
  @Nullable
  String getTaskId();

  @Override
  @Nullable
  String getProcessInstanceId();

  @Override
  default String getRootProcessInstanceId() {
    return getProcessInstanceId();
  }

  @Override
  @Nullable
  String getFullMessage();

  @Override
  @Nullable
  Date getRemovalTime();
}
