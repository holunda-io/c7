package org.camunda.community.mockito.function;


import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.community.mockito.function.NameForType.juelNameFor;
import static org.mockito.Mockito.mock;

import javax.inject.Named;

import org.junit.jupiter.api.Test;

public class NameForExpressionTypeTest {

  public static class PojoBean {
  }

  @Named
  public static class NamedBean {
  }

  @Named("bar")
  public static class BarNamedBean {
  }

  @Test
  public void resolves_pojo() {
    assertThat(juelNameFor(PojoBean.class)).isEqualTo("pojoBean");
  }

  @Test
  public void resolves_named_default() {
    assertThat(juelNameFor(NamedBean.class)).isEqualTo("namedBean");
  }

  @Test
  public void resolves_named_bar() {
    assertThat(juelNameFor(BarNamedBean.class)).isEqualTo("bar");
  }

  @Test
  public void gets_name_for_mock_byClass() throws Exception {
    assertThat(NameForType.juelNameFor(mock(NamedBean.class))).isEqualTo("namedBean");
  }

  @Test
  public void gets_name_for_mock_byAnnotation() throws Exception {
    assertThat(NameForType.juelNameFor(mock(BarNamedBean.class))).isEqualTo("bar");
  }

  @Test
  public void getTypeOfMock() throws Exception {
    assertThat(NameForType.typeOf(mock(BarNamedBean.class)).getSimpleName()).isEqualTo(BarNamedBean.class.getSimpleName());
  }
}
