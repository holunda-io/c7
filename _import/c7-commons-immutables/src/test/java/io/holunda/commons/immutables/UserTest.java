package io.holunda.commons.immutables;

import static io.holunda.commons.immutables.Assertions.assertThat;
import static io.holunda.commons.immutables._Fixtures.USER;
import static io.holunda.commons.immutables._Fixtures.USER_ID;

@SuppressWarnings({"java:S2699"})
public class UserTest extends _BasicImmutableTest<ImmutableUser>{

  public UserTest() {
    super(ImmutableUser.class);
  }

  @Override
  public void factory_method() {
    final ImmutableUser dto = createDto();

    assertThat(dto).hasId(USER_ID);
    assertThat(dto).hasFirstName(USER.getFirstName());
    assertThat(dto).hasLastName(USER.getLastName());
    assertThat(dto).hasEmail(USER.getEmail());
    assertThat(dto).hasPassword(USER.getPassword());
  }

  @Override
  public void create_minimal_dto() {
    ImmutableUser.builder()
      .id(USER_ID)
      .build();
  }

  @Override
  ImmutableUser createDto() {
    return CamundaImmutables.user(USER);
  }
}
