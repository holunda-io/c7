package io.holunda.commons.immutables.repository;

import org.junit.Test;


public class ResourceDefinitionTest {

  @Test
  public void name() {
    ImmutableResourceDefinition.builder()
      .id("id")
      .category("category")
      .key("key")
      .version(0)
      .resourceName("name")
      .deploymentId("dId")
      .name("name")
      .diagramResourceName("res.name")
      .build();
  }
}
