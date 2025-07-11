package io.holunda.camunda.platform.adminprocess.form

import io.holunda.camunda.platform.adminprocess.TestFixtures
import io.holunda.camunda.platform.adminprocess.TestFixtures.extensionElements
import io.holunda.camunda.platform.adminprocess.TestFixtures.flatten
import io.holunda.camunda.platform.adminprocess.TestFixtures.xml
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DateFieldTest {

  @Test
  fun `create date start form field`() {
    val field = DateField("foo", "Foo")

    val expected = """
      <camunda:formData>
        <camunda:formField id="foo" label="Foo" type="date"/>
      </camunda:formData>
    """.trimIndent().flatten()

    val generated = TestFixtures.createProcess(field).xml().extensionElements()

    Assertions.assertThat(generated).isEqualTo(expected)
  }
}
