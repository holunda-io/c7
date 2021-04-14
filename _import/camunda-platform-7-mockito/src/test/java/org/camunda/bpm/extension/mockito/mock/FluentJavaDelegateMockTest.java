package org.camunda.bpm.extension.mockito.mock;

import io.holunda.camunda.bpm.data.CamundaBpmData;
import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.extension.mockito.DelegateExpressions;
import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FluentJavaDelegateMockTest {

  private static final String BEAN_NAME = "foo";
  private static final String MESSAGE = "message";

  private final FluentJavaDelegateMock delegate = DelegateExpressions.registerJavaDelegateMock(BEAN_NAME);
  private final DelegateExecutionFake execution = DelegateExecutionFake.of();

  @Test
  public void throws_bpmnError() {
    delegate.onExecutionThrowBpmnError("code", MESSAGE);

    // test succeeds when exception is thrown
    assertThatThrownBy(() -> delegate.execute(execution))
      .isInstanceOf(BpmnError.class)
      .hasMessage(MESSAGE);

  }

  @Test
  public void throws_exception() {
    delegate.onExecutionThrowException(new NullPointerException());

    assertThatThrownBy(() -> delegate.execute(execution))
      .isInstanceOf(NullPointerException.class);
  }

  @Test
  public void set_single_variable() throws Exception {
    delegate.onExecutionSetVariable("foo", "bar");

    delegate.execute(execution);

    assertThat(execution.hasVariable("foo")).isTrue();
    assertThat((String) execution.getVariable("foo")).isEqualTo("bar");
  }

  @Test
  public void set_single_variable_via_factory() throws Exception {
    VariableFactory<String> foo = CamundaBpmData.stringVariable("foo");

    delegate.onExecutionSetVariable(foo, "bar");

    delegate.execute(execution);

    assertThat(execution.hasVariable("foo")).isTrue();
    assertThat(foo.from(execution).get()).isEqualTo("bar");
  }
}
