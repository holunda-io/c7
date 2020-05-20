package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.task.Attachment;
import org.immutables.value.Value;

@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableAttachment.class)
@JsonSerialize(as = ImmutableAttachment.class)
public interface _Attachment  extends Attachment {


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
