/*-
 * #%L
 * Camunda Process Test Coverage Engine Platform 7
 * %%
 * Copyright (C) 2019 - 2024 Camunda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
/*
 * Copyright 2020 FlowSquad GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
