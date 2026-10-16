package org.camunda.community.mockito.function;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.community.mockito.function.NameForType.juelNameFor;

public class NameForExpressionTypeTest {

  public static class PojoBean {
  }

  @Component
  public static class ComponentBean {
  }

  @Component("bar")
  public static class BarComponentBean {
  }

  @Service
  public static class ServiceBean {
  }

  @Service("bar")
  public static class BarServiceBean {
  }

  @Test
  public void resolves_component() {
    assertThat(juelNameFor(ComponentBean.class)).isEqualTo("componentBean");
  }

  @Test
  public void resolves_named_component() {
    assertThat(juelNameFor(BarComponentBean.class)).isEqualTo("bar");
  }

  @Test
  public void resolves_service() {
    assertThat(juelNameFor(ServiceBean.class)).isEqualTo("serviceBean");
  }

  @Test
  public void resolves_named_service() {
    assertThat(juelNameFor(BarServiceBean.class)).isEqualTo("bar");
  }

  @Test
  public void gets_value_from_component() {
     assertThat(NameForType.GET_VALUE.apply(BarComponentBean.class.getAnnotation(Component.class))).isEqualTo("bar");
     assertThat(NameForType.GET_VALUE.apply(ComponentBean.class.getAnnotation(Component.class))).isEqualTo("");
  }

  @Test
  public void gets_value_from_service() {
     assertThat(NameForType.GET_VALUE.apply(BarServiceBean.class.getAnnotation(Service.class))).isEqualTo("bar");
     assertThat(NameForType.GET_VALUE.apply(ServiceBean.class.getAnnotation(Service.class))).isEqualTo("");
  }

}
