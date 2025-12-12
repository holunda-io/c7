package org.camunda.community.process_test_coverage.engine.platform7

import org.camunda.bpm.engine.impl.bpmn.helper.BpmnProperties
import org.camunda.bpm.engine.impl.event.CompensationEventHandler
import org.camunda.bpm.engine.impl.interceptor.CommandContext
import org.camunda.bpm.engine.impl.persistence.entity.EventSubscriptionEntity
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl
import org.camunda.community.process_test_coverage.core.model.Collector
import org.camunda.community.process_test_coverage.core.model.Event
import org.camunda.community.process_test_coverage.core.model.EventSource
import org.camunda.community.process_test_coverage.core.model.EventType
import java.time.Instant

/**
 * Handler for Compensation Boundary Events
 */
class CompensationEventCoverageHandler : CompensationEventHandler() {
    /**
     * The collector the currently running coverage.
     */
    private lateinit var coverageState: Collector

    fun setCoverageState(coverageState: Collector) {
        this.coverageState = coverageState
    }

    override fun handleEvent(
        eventSubscription: EventSubscriptionEntity, payload: Any?, localPayload: Any?,
        businessKey: String?, commandContext: CommandContext
    ) {
        addCompensationEventCoverage(eventSubscription)
        super.handleEvent(eventSubscription, payload, localPayload, businessKey, commandContext)
    }

    private fun addCompensationEventCoverage(eventSubscription: EventSubscriptionEntity) {
        val activity = eventSubscription.activity

        // Get process definition key
        val processDefinition = activity.processDefinition as ProcessDefinitionEntity

        // Get compensation boundary event ID
        val sourceEvent = activity.getProperty(BpmnProperties.COMPENSATION_BOUNDARY_EVENT.name) as ActivityImpl?

        if (sourceEvent != null) {
            require(this::coverageState.isInitialized) { "Coverage state must be initialized" }
            val sourceEventId = sourceEvent.activityId

            // Register event
            val startEvent = Event(
                EventSource.FLOW_NODE,
                EventType.START,
                sourceEventId,
                "boundaryEvent",
                processDefinition.key,
                Instant.now().epochSecond
            )
            coverageState.addEvent(startEvent)
            val endEvent = Event(
                EventSource.FLOW_NODE,
                EventType.END,
                sourceEventId,
                "boundaryEvent",
                processDefinition.key,
                Instant.now().epochSecond
            )
            coverageState.addEvent(endEvent)
        }
    }


}
