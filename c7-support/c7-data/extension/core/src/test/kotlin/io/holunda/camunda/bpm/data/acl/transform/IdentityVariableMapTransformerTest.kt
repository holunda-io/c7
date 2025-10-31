package io.holunda.camunda.bpm.data.acl.transform

import io.holunda.camunda.bpm.data.Writers.C7.builder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IdentityVariableMapTransformerTest {

  @Test
  fun `should pass the input to output`() {

    val vars = builder().build()

    assertThat(IdentityVariableMapTransformer.transform(vars))
      .isEqualTo(vars) // equals comparison
      .isSameAs(vars) // == comparison
  }
}
