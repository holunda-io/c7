package io.holunda.commons.immutables.externaltask;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.holunda.commons.immutables._Fixtures.uuid;

public class ExternalTaskTest {

  @Test
  public void tenantId_is_optional() {
    var t = ImmutableExternalTask.builder()
      .id(uuid())
      .topicName("r")
      .workerId("w")
      .lockExpirationTime(new Date())
      .retries(2)
      .build();
  }
}
