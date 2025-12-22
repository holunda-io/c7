package org.camunda.community.process_test_coverage.sonar

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.sonar.api.config.Configuration

class BpmnLanguageTest {

  @Test
  fun should_load_default_suffixes_with_no_custom_configuration() {
    val config = mock<Configuration> {
      on { getStringArray(ProcessTestCoveragePlugin.FILE_SUFFIXES_KEY) } doReturn arrayOf()
    }
    val language = BpmnLanguage(config)
    assertThat(language.fileSuffixes).containsExactly(".bpmn")
  }

  @Test
  fun should_load_suffixes_from_configuration() {
    val config = mock<Configuration> {
      on { getStringArray(ProcessTestCoveragePlugin.FILE_SUFFIXES_KEY) } doReturn arrayOf(".bpmnx")
  }
    val language = BpmnLanguage(config)
    assertThat(language.fileSuffixes).containsExactly(".bpmnx")
  }

  @Test
  fun should_get_key_and_name() {
    val config = mock<Configuration> {
      on { getStringArray(ProcessTestCoveragePlugin.FILE_SUFFIXES_KEY) } doReturn arrayOf()
    }
    val language = BpmnLanguage(config)
    assertThat(language.key).isEqualTo(BpmnLanguage.KEY)
    assertThat(language.name).isEqualTo(BpmnLanguage.NAME)
  }

}
