package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.platform.adminprocess.TestFixtures.createProcess
import io.holunda.camunda.platform.adminprocess.TestFixtures.extensionElements
import io.holunda.camunda.platform.adminprocess.TestFixtures.flatten
import io.holunda.camunda.platform.adminprocess.TestFixtures.xml
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BooleanFieldTest {

  @Test
  fun `create boolean start form field`() {
    val field = BooleanField("foo", "Foo")

    val expected = """
      <camunda:formData>
        <camunda:formField id="foo" label="Foo" type="boolean"/>
      </camunda:formData>
    """.trimIndent().flatten()

    val generated = createProcess(field).xml().extensionElements()

    assertThat(generated).isEqualTo(expected)
  }
}
