package org.camunda.community.process_test_coverage.engine.platform7

import org.camunda.bpm.engine.impl.context.Context
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.instance.FlowNode
import org.camunda.bpm.model.bpmn.instance.Process
import org.camunda.bpm.model.bpmn.instance.SequenceFlow
import org.camunda.bpm.model.xml.instance.ModelElementInstance
import org.camunda.community.process_test_coverage.core.engine.ModelProvider
import org.camunda.community.process_test_coverage.core.model.Model
import java.util.stream.Collectors

/**
 * Provider that is used to load processes from the engine.
 * The Thread Local Context of Camunda is used for this.
 */
class ExecutionContextModelProvider: ModelProvider {

    override fun getModel(key: String): Model {

        val services = Context.getBpmnExecutionContext()
            .execution
            .processEngineServices
        val processDefinition = services.repositoryService.createProcessDefinitionQuery()
            .processDefinitionKey(key)
            .latestVersion()
            .singleResult()
        val modelInstance = services.repositoryService.getBpmnModelInstance(
            processDefinition.id
        )
        val definitionFlowNodes = getExecutableFlowNodes(modelInstance.getModelElementsByType(FlowNode::class.java), key)
        val definitionSequenceFlows = getExecutableSequenceNodes(modelInstance.getModelElementsByType(SequenceFlow::class.java), definitionFlowNodes)

        return Model(
            key,
            definitionFlowNodes.size + definitionSequenceFlows.size,
            processDefinition.versionTag,
            Bpmn.convertToString(modelInstance)
        )
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
        return when (node) {
            is Process -> node.isExecutable && node.id == processId
            else -> isExecutable(node.parentElement, processId)
        }
    }
}