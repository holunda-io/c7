package org.camunda.community.process_test_coverage.core.engine

import java.lang.reflect.Method
import java.util.regex.Pattern


fun Class<*>.isExcluded(inclusionPatterns: List<String> = emptyList()) =
    annotations.any { it is ExcludeFromProcessCoverage } || !matchesInclusionPattern(this, inclusionPatterns)

fun Method.isExcluded() = annotations.any { it is ExcludeFromProcessCoverage }

private fun matchesInclusionPattern(testClass: Class<*>, inclusionPatterns: List<String>) =
    inclusionPatterns.isEmpty() || inclusionPatterns.simpleMatch(testClass.name)

private fun Collection<String>.simpleMatch(text: String): Boolean {
  return this.any { pattern ->
    val regexString = Pattern.quote(pattern).replace("*", "\\E.*\\Q")
    text.matches(regexString.toRegex())
  }
}
