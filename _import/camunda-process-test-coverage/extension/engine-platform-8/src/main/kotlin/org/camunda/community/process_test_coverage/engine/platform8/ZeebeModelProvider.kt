package org.camunda.community.process_test_coverage.engine.platform8

import io.camunda.zeebe.model.bpmn.Bpmn.*
import io.camunda.zeebe.model.bpmn.instance.FlowNode
import io.camunda.zeebe.model.bpmn.instance.IntermediateThrowEvent
import io.camunda.zeebe.model.bpmn.instance.LinkEventDefinition
import io.camunda.zeebe.model.bpmn.instance.Process
import io.camunda.zeebe.model.bpmn.instance.SequenceFlow
import io.camunda.zeebe.process.test.assertions.BpmnAssert.getRecordStream
import org.camunda.community.process_test_coverage.core.model.Model
import org.camunda.bpm.model.xml.instance.ModelElementInstance
import org.camunda.community.process_test_coverage.core.engine.ModelProvider
import java.io.ByteArrayInputStream
import java.util.stream.Collectors

/**
 * Provider that is used to load processes from the engine.
 * The record stream from zeebe is used for this.
 */
class ZeebeModelProvider: ModelProvider {

    override fun getModel(key: String): Model {
        val process = getRecordStream().processRecords()
                .first { it.value.bpmnProcessId == key }

        return process?.value?.let {
            val modelInstance = readModelFromStream(ByteArrayInputStream(it.resource))
            val definitionFlowNodes = getExecutableFlowNodes(modelInstance.getModelElementsByType(FlowNode::class.java), key)
            val definitionSequenceFlows = getExecutableSequenceNodes(modelInstance.getModelElementsByType(SequenceFlow::class.java), definitionFlowNodes)
            Model(
                key,
                definitionFlowNodes.size + definitionSequenceFlows.size,
                "${it.version}",
                convertToString(modelInstance)
            )
        } ?: throw IllegalArgumentException()

    }

    private fun getExecutableFlowNodes(flowNodes: Collection<FlowNode>, processId: String): Set<FlowNode> {
        return flowNodes.stream()
            .filter { node: FlowNode? -> isExecutable(node, processId) }
            .collect(Collectors.toSet())
    }

    private fun getExecutableSequenceNodes(sequenceFlows: Collection<SequenceFlow>, definitionFlowNodes: Set<FlowNode>): Set<SequenceFlow> {
        return sequenceFlows.stream()
            .filter { s: SequenceFlow -> definitionFlowNodes.contains(s.source) }
            .collect(Collectors.toSet())
    }

    private fun isExecutable(node: ModelElementInstance?, processId: String): Boolean {
        if (node == null) {
            return false
        }
        return if (node is Process) {
            node.isExecutable && node.id == processId
        } else if (node is IntermediateThrowEvent) {
            node.eventDefinitions.none { it is LinkEventDefinition }
        } else {
            isExecutable(node.parentElement, processId)
        }
    }

}