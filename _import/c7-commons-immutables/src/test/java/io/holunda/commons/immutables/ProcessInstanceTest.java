package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.CamundaImmutablesTest.uuid;
import static io.holunda.commons.immutables.JacksonHelper.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

import io.holunda.commons.immutables.CamundaImmutablesTest.BasicImmutableTest;
import io.holunda.commons.immutables.JacksonHelper.JsonMapper;
import java.util.UUID;
import org.junit.Test;

public class ProcessInstanceTest implements BasicImmutableTest {

  private static final String ID = UUID.randomUUID().toString();
  private final JsonMapper<ImmutableProcessInstance> mapper = jsonMapper(ImmutableProcessInstance.class);

  @Test
  @Override
  public void create_minimal_dto() {

    ImmutableProcessInstance dto = ImmutableProcessInstance.builder()
      .id(ID)
      .processDefinitionId(uuid())
      .build();

    assertThat(dto.getProcessInstanceId()).isEqualTo(ID);

  }

  @Test
  @Override
  public void convert_json() {
    ImmutableProcessInstance dto = ImmutableProcessInstance.builder()
      .id(ID)
      .processDefinitionId(uuid())
      .build();

    String json = mapper.toJson(dto);

    assertThat(mapper.fromJson(json)).isEqualTo(dto);
  }
}
