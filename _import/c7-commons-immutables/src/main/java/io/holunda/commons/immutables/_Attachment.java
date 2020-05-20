package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CurrentTimestamp;
import java.util.Date;
import org.camunda.bpm.engine.task.Attachment;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableAttachment.class)
@JsonSerialize(as = ImmutableAttachment.class)
public interface _Attachment  extends Attachment, CurrentTimestamp {

  @Override
  @Nullable
  String getName();

  @Override
  @Nullable
  String getDescription();

  @Override
  @Nullable
  String getType();

  @Override
  @Nullable
  String getTaskId();

  @Override
  String getProcessInstanceId();

  @Override
  @Nullable
  String getUrl();

  @Override
  default Date getCreateTime() {
    return getNow().get();
  }

  @Override
  @Nullable
  default String getRootProcessInstanceId() {
    return getProcessInstanceId();
  }

  @Override
  @Nullable
  Date getRemovalTime();

  @Override
  @Deprecated
  default void setName(String name) {
    throw UNMODIFIABLE;
  }

  @Override
  @Deprecated
  default void setDescription(String description) {
    throw UNMODIFIABLE;
  }
}
