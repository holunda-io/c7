package io.holunda.commons.immutables.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.form.CamundaFormRef;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@CamundaPojoStyle
@JsonSerialize(as = ImmutableFormRef.class)
@JsonDeserialize(as = ImmutableFormRef.class)
@SuppressWarnings("java:S114")
public interface _FormRef extends CamundaFormRef {

  @Override
  String getKey();

  @Override
  String getBinding();

  @Nullable
  @Override
  Integer getVersion();
}
