package org.camunda.community.process_test_coverage.core.engine

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IsExcludedTest {

  @ExcludeFromProcessCoverage
  class ExcludedFromProcessCoverage

  class NotIncludedByPattern

  class IncludedByPattern

  class IncludedByDefault

  @Test
  fun `should not exclude if inclusion patterns match`() {
    assertFalse { IncludedByPattern::class.java.isExcluded(listOf("*Included*")) }
    assertFalse { IncludedByPattern::class.java.isExcluded(listOf($$"org.camunda.community.process_test_coverage.core.engine.IsExcludedTest$Included*Pattern")) }
    assertFalse { IncludedByPattern::class.java.isExcluded(listOf("*ByPattern")) }
  }

  @Test
  fun `should not exclude if no inclusion pattern an not excluded`() {
    assertFalse { IncludedByDefault::class.java.isExcluded() }
  }

  @Test
  fun `should be excluded if inclusions patterns do not match`() {
    assertTrue { NotIncludedByPattern::class.java.isExcluded(listOf("Included*")) }
    assertTrue { NotIncludedByPattern::class.java.isExcluded(listOf("Included*Pattern")) }
  }

  @Test
  fun `should be excluded if annotation present`() {
    assertTrue { ExcludedFromProcessCoverage::class.java.isExcluded() }
  }

}
