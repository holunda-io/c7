package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutablesTest.uuid;
import static io.holunda.commons.immutables.JacksonHelper.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

import io.holunda.commons.immutables.CamundaImmutablesTest.BasicImmutableTest;
import io.holunda.commons.immutables.JacksonHelper.JsonMapper;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.IdentityLinkType;
import org.junit.Test;

public class IdentityLinkTest implements BasicImmutableTest {

  private final JsonMapper<ImmutableIdentityLink> mapper = jsonMapper(ImmutableIdentityLink.class);

  @Test
  @Override
  public void create_minimal_dto() {
    ImmutableIdentityLink dto = ImmutableIdentityLink.builder()
      .id(uuid())
      .type(IdentityLinkType.ASSIGNEE)
      .taskId(uuid())
      .build();
  }

  @Test
  @Override
  public void convert_json() {
    ImmutableIdentityLink dto = ImmutableIdentityLink.builder()
      .id(uuid())
      .type(IdentityLinkType.ASSIGNEE)
      .taskId(uuid())
      .groupId("group")
      .tenantId("tenant")
      .processDefId("defId")
      .userId("user")
      .build();

    String json = mapper.toJson(dto);

    assertThat(mapper.fromJson(json)).isEqualTo(dto);
  }
}
