package org.camunda.community.mockito.answer;

import org.camunda.bpm.engine.delegate.VariableScope;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbstractAnswerTest {

  private final AbstractAnswer<VariableScope> answer = spy(new AbstractAnswer<VariableScope>() {

    @Override
    protected void answer(final VariableScope parameter) {

    }
  });

  @Mock
  private VariableScope variableScope;

  @Mock
  private InvocationOnMock invocationOnMock;

  @Test
  public void shouldDelegateToGenericAnswer() throws Throwable {
    when(invocationOnMock.getArguments()).thenReturn(new Object[]{variableScope});
    answer.answer(invocationOnMock);
    verify(answer).answer(variableScope);
  }

}
