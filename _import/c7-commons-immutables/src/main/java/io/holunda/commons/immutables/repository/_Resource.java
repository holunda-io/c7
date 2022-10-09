package io.holunda.commons.immutables.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables._config.CamundaPojoStyle;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.Resource;
import org.immutables.value.Value;

/**
 * @see io.holunda.commons.immutables.CamundaImmutables#resource(Resource)
 */
@Value.Immutable
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableResource.class)
@JsonSerialize(as = ImmutableResource.class)
@SuppressWarnings("java:S114")
public interface _Resource extends Resource {
  @Override
  String getId();

  @Override
  String getName();

  @Override
  String getDeploymentId();

  @Override
  default byte[] getBytes() {
    return new byte[0];
  }
}
