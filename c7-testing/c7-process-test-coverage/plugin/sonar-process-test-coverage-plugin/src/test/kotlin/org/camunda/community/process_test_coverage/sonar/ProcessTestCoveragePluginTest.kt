package org.camunda.community.process_test_coverage.sonar

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.sonar.api.Plugin
import org.sonar.api.SonarRuntime


class ProcessTestCoveragePluginTest {

    @Test
    fun testExtensions() {
        val runtime = mock<SonarRuntime> {  }
        val context = Plugin.Context(runtime)
        val plugin = ProcessTestCoveragePlugin()
        plugin.define(context)
        assertEquals(8, context.extensions.size)
    }

}
