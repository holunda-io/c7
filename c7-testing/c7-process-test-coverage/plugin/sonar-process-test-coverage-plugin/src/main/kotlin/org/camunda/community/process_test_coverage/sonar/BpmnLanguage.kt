package org.camunda.community.process_test_coverage.sonar

import org.sonar.api.config.Configuration
import org.sonar.api.resources.AbstractLanguage

class BpmnLanguage(private val config: Configuration) : AbstractLanguage(KEY, NAME) {

  companion object {
    const val NAME = "BPMN"
    const val KEY = "bpmn"
    const val DEFAULT_FILE_SUFFIXES = ".bpmn"
  }

  override fun getFileSuffixes(): Array<String> {
    var suffixes = config.getStringArray(ProcessTestCoveragePlugin.FILE_SUFFIXES_KEY)
      .filterNotNull()
      .toTypedArray()
    if (suffixes.isEmpty()) {
      suffixes = arrayOf(DEFAULT_FILE_SUFFIXES)
    }
    return suffixes
  }

}
