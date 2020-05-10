package io.holunda.commons.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.holunda.commons.immutables.CamundaImmutables.Facets.WithBusinessKey;
import io.holunda.commons.immutables.CamundaImmutables.ImmutablesConfiguration.CamundaPojoStyle;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable(prehash = true)
@CamundaPojoStyle
@JsonDeserialize(as = ImmutableProcessInstance.class)
@JsonSerialize(as = ImmutableProcessInstance.class)
public interface _ProcessInstance extends ProcessInstanceWithVariables, _Execution, WithBusinessKey {

  @Override
  @Value.Default
  default VariableMap getVariables() {
    return Variables.createVariables();
  }

  @Override
  @Nullable
  String getCaseInstanceId();

}
