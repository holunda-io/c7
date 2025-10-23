package io.holunda.camunda.bpm.data.writer;

import io.holunda.camunda.bpm.data.CamundaBpmData;
import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.jupiter.api.Test;

import static io.holunda.camunda.bpm.data.Writers.C7.writer;
import static org.assertj.core.api.Assertions.assertThat;

public class VariableMapWriterTest {

  private static final VariableFactory<String> STRING = CamundaBpmData.stringVariable("myString");

  private final VariableMap variables = Variables.createVariables();

  @Test
  public void testSet() {
    writer(variables)
      .set(STRING, "value");
    assertThat(variables.get(STRING.getName())).isEqualTo("value");
  }

  @Test
  public void testRemove() {
    STRING.on(variables).set("value");
    writer(variables)
      .remove(STRING);
    assertThat(variables).isEmpty();
  }

}
