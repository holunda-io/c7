package org.camunda.bpm.extension.process_test_coverage.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.extension.process_test_coverage.util.CoveredElementComparator;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.junit.Assert;

/**
 * Test class coverage model. The class coverage is an aggregation of all test method coverages.
 * 
 * @author okicir
 *
 */
public class ClassCoverage implements Coverage {
    
    /**
     * Map connecting the test method to the test method run coverage.
     */
    private Map<String, MethodCoverage> testToDeploymentCoverage = new HashMap<String, MethodCoverage>();

    /**
     * Adds a covered element to the test method coverage.
     * 
     * @param testName
     * @param coveredElement
     */
    public void addCoveredElement(String testName, CoveredElement coveredElement) {
        testToDeploymentCoverage.get(testName).addCoveredElement(coveredElement);
    }
    
    /**
     * Retrieves a test methods coverage.
     * 
     * @param testName The name of the test method.
     * @return
     */
    public MethodCoverage getTestMethodCoverage(String testName) {
        return testToDeploymentCoverage.get(testName);
    }
    
    /**
     * Add a test method coverage to the class coverage.
     * 
     * @param testName
     * @param testCoverage
     */
    public void addTestMethodCoverage(String testName, MethodCoverage testCoverage) {
        testToDeploymentCoverage.put(testName, testCoverage);
    }
    
    /**
     * Retrieves the class coverage percentage.
     * All covered test methods' elements are aggregated and checked against the 
     * process definition elements.
     * 
     * @return The coverage percentage.
     */
    public double getCoveragePercentage() {
        
        // All deployments must be the same, so we take the first one
        final MethodCoverage anyDeployment = getAnyMethodCoverage();
        
        final Set<FlowNode> definitionsFlowNodes = anyDeployment.getProcessDefinitionsFlowNodes();
        final Set<SequenceFlow> definitionsSeqenceFlows = anyDeployment.getProcessDefinitionsSequenceFlows();
        
        final Set<CoveredActivity> coveredFlowNodes = getCoveredFlowNodes();
        final Set<CoveredSequenceFlow> coveredSequenceFlows = getCoveredSequenceFlows();
        
        final double bpmnElementsCount = definitionsFlowNodes.size() + definitionsSeqenceFlows.size();
        final double coveredElementsCount = coveredFlowNodes.size() + coveredSequenceFlows.size();
        
        return coveredElementsCount / bpmnElementsCount;
    }
    
    /**
     * Retrieves the covered flow nodes.
     * Flow nodes with the same element ID but different process definition keys are retained. {@see CoveredElementComparator}
     * 
     * @return A set of covered flow nodes.
     */
    public Set<CoveredActivity> getCoveredFlowNodes() {

        final Set<CoveredActivity> coveredFlowNodes = new TreeSet<CoveredActivity>(CoveredElementComparator.instance());

        for (MethodCoverage deploymentCoverage : testToDeploymentCoverage.values()) {

            coveredFlowNodes.addAll(deploymentCoverage.getCoveredFlowNodes());
        }

        return coveredFlowNodes;
    }
    
    /**
     * Retrieves a set of covered flow node IDs for the given process definition key.
     * 
     */
    public Set<String> getCoveredFlowNodeIds(String processDefinitionKey) {
        
        final Set<String> coveredFlowNodeIds = new HashSet<String>();
        for (MethodCoverage methodCoverage : testToDeploymentCoverage.values()) {

            coveredFlowNodeIds.addAll(methodCoverage.getCoveredFlowNodeIds(processDefinitionKey));
        }

        return coveredFlowNodeIds;
    }
    
    /**
     * Retrieves the covered sequence flows.
     * Sequence flows with the same element ID but different process definition keys are retained. {@see CoveredElementComparator}
     * 
     * @return A set of covered flow nodes.
     */
    public Set<CoveredSequenceFlow> getCoveredSequenceFlows() {

        final Set<CoveredSequenceFlow> coveredSequenceFlows = new TreeSet<CoveredSequenceFlow>(CoveredElementComparator.instance());

        for (MethodCoverage deploymentCoverage : testToDeploymentCoverage.values()) {

            coveredSequenceFlows.addAll(deploymentCoverage.getCoveredSequenceFlows());

        }

        return coveredSequenceFlows;
    }

    /**
     * Retrieves a set of covered sequence flow IDs for the given process definition key.
     * 
     */
    public Set<String> getCoveredSequenceFlowIds(String processDefinitionKey) {
        
        final Set<String> coveredSequenceFlowIds = new HashSet<String>();
        for (MethodCoverage methodCoverage : testToDeploymentCoverage.values()) {
            
            coveredSequenceFlowIds.addAll(methodCoverage.getCoveredSequenceFlowIds(processDefinitionKey));
        }
        
        return coveredSequenceFlowIds;
    }
    
    /**
     * Retrieves the process definitions of the coverage test.
     * Since there are multiple deployments (one for each test method) the first
     * set of process definitions found is return.
     */
    public Set<ProcessDefinition> getProcessDefinitions() {
        return getAnyMethodCoverage().getProcessDefinitions();
    }
    
    /**
     * Retrieves the first method coverage found.
     * 
     * @return
     */
    private MethodCoverage getAnyMethodCoverage() {
        
        // All deployments must be the same, so we take the first one
        final MethodCoverage anyDeployment = testToDeploymentCoverage.values().iterator().next();
        return anyDeployment;
    }
    
    /**
     * Asserts if all method deployments are equal. (BPMNs with the same business keys) 
     */
    public void assertAllDeploymentsEqual() {
        
        Set<ProcessDefinition> processDefinitions = null;
        for (MethodCoverage methodCoverage : testToDeploymentCoverage.values()) {
            
            Set<ProcessDefinition> deploymentProcessDefinitions = methodCoverage.getProcessDefinitions();
            
            if (processDefinitions == null) {
                processDefinitions = deploymentProcessDefinitions;
            }
                
            Assert.assertEquals("Class coverage can only be calculated if all tests deploy the same BPMN resources.", 
                    processDefinitions, deploymentProcessDefinitions);
            
        }
        
    }
    
}