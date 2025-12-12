package org.camunda.community.process_test_coverage.engine.platform7

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl
import java.util.*

/**
 * Helper methods to configure the process coverage extensions on a given ProcessEngineConfigurationImpl
 */
object ProcessCoverageConfigurator {

    @JvmStatic
    fun initializeProcessCoverageExtensions(configuration: ProcessEngineConfigurationImpl) {
        initializeElementCoverageParseListener(configuration)
        initializeCompensationEventHandler(configuration)
    }

    private fun initializeElementCoverageParseListener(configuration: ProcessEngineConfigurationImpl) {
        var bpmnParseListeners = configuration.customPostBPMNParseListeners
        if (bpmnParseListeners == null) {
            bpmnParseListeners = LinkedList()
            configuration.customPostBPMNParseListeners = bpmnParseListeners
        }
        bpmnParseListeners.add(ElementCoverageParseListener())
    }

    private fun initializeCompensationEventHandler(configuration: ProcessEngineConfigurationImpl) {
        if (configuration.customEventHandlers == null) {
            configuration.customEventHandlers = LinkedList()
        }
        configuration.customEventHandlers.add(CompensationEventCoverageHandler())
    }
}
