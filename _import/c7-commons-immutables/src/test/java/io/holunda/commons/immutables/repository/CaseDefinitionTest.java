package io.holunda.commons.immutables.repository;

import io.holunda.commons.immutables.CamundaImmutables;
import io.holunda.commons.immutables._BasicImmutableTest;
import io.holunda.commons.immutables._Fixtures;

import static io.holunda.commons.immutables._Fixtures.*;

@SuppressWarnings({"java:S2699", "java:S2187"})
public class CaseDefinitionTest extends _BasicImmutableTest<ImmutableCaseDefinition> {

  public CaseDefinitionTest() {
    super(ImmutableCaseDefinition.class);
  }

  @Override
  public void create_minimal_dto() {
    ImmutableCaseDefinition.builder()
      .resourceName("res")
      .id(uuid())
      .category("cat")
      .name("def-name")
      .key("the-key")
      .deploymentId("id")
      .diagramResourceName("dia/res")
      .build();
  }

  @Override
  public void factory_method() {
    var dto = createDto();

    ImmutableCaseDefinitionAssert.assertThat(dto).hasId(CASE_DEFINITION_ID)
      .hasTenantId(TENANT_ID)
      .hasDeploymentId(DEPLOYMENT_ID)
      .hasCategory("category")
      .hasHistoryTimeToLive(19)
      .hasVersion(1)
      .hasName("CaseDefinition")
      .hasKey(CASE_DEFINITION_KEY)
      .hasResourceName(CASE_DEFINITION_KEY + ".cmmn")
      .hasDeploymentId(DEPLOYMENT_ID)
      .hasDiagramResourceName("file/" + CASE_DEFINITION_KEY + ".cmmn")
    ;
  }

  @Override
  protected ImmutableCaseDefinition createDto() {
    return CamundaImmutables.caseDefinition(_Fixtures.CASE_DEFINITION);
  }
}
