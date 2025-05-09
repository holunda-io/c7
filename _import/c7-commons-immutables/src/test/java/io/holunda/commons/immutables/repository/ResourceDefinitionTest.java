package io.holunda.commons.immutables.repository;


import org.junit.jupiter.api.Test;

public class ResourceDefinitionTest {

  @Test
  public void resource_definition() {
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
