package org.camunda.bpm.extension.process_test_coverage.junit.rules;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.extension.process_test_coverage.model.ClassCoverage;
import org.camunda.bpm.extension.process_test_coverage.model.CoveredElement;
import org.camunda.bpm.extension.process_test_coverage.model.MethodCoverage;
import org.camunda.bpm.extension.process_test_coverage.model.ProcessCoverage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * State tracking the current class and method coverage run.
 * 
 * @author grossax
 * @author z0rbas
 */
public class DefaultCoverageTestRunState implements CoverageTestRunState {

    private Logger log = Logger.getLogger(DefaultCoverageTestRunState.class.getCanonicalName());

    /**
     * The actual class coverage object.
     */
    private ClassCoverage classCoverage = new ClassCoverage();

    /**
     * The test class name.
     */
    private String testClassName;

    /**
     * The name of the currently executing test method.
     */
    private String currentTestMethodName;

    /**
     * A list of process definition keys excluded from the test run.
     */
    private List<String> excludedProcessDefinitionKeys;

    /**
     * Adds the covered element to the current test run coverage.
     * 
     * @param coveredElement
     */
    @Override
    public void addCoveredElement(/* @NotNull */ CoveredElement coveredElement) {

        if (!isExcluded(coveredElement)) {
            if (log.isLoggable(Level.FINE)) {
                log.info("addCoveredElement(" + coveredElement + ")");
            }

            classCoverage.addCoveredElement(currentTestMethodName, coveredElement);
        }

    }

    /**
     * Mark a covered element execution as ended.
     * 
     * @param coveredElement
     */
    @Override
    public void endCoveredElement(CoveredElement coveredElement) {

        if (!isExcluded(coveredElement)) {
            if (log.isLoggable(Level.FINE)) {
                log.info("endCoveredElement(" + coveredElement + ")");
            }

            classCoverage.endCoveredElement(currentTestMethodName, coveredElement);
        }

    }

    /**
     * Adds a test method to the class coverage.
     * 
     * @param processEngine
     * @param deploymentId
     *            The deployment ID of the test method run. (Hint: Every test
     *            method run has its own deployment.)
     * @param processDefinitions
     *            The process definitions of the test method deployment.
     * @param testName
     *            The name of the test method.
     */
    @Override
    public void initializeTestMethodCoverage(ProcessEngine processEngine, String deploymentId,
            List<ProcessDefinition> processDefinitions, String testName) {

        final MethodCoverage testCoverage = new MethodCoverage(deploymentId);
        for (ProcessDefinition processDefinition : processDefinitions) {

            // Construct the pristine coverage object

            // TODO decide on the builders fate
            final ProcessCoverage processCoverage = new ProcessCoverage(processEngine, processDefinition);

            testCoverage.addProcessCoverage(processCoverage);
        }

        classCoverage.addTestMethodCoverage(testName, testCoverage);
    }

    /**
     * Retrieves the coverage for a test method.
     * 
     * @param testName
     * @return
     */
    @Override
    public MethodCoverage getTestMethodCoverage(String testName) {
        return classCoverage.getTestMethodCoverage(testName);
    }

    /**
     * Retrieves the currently executing test method coverage.
     * 
     * @return
     */
    @Override
    public MethodCoverage getCurrentTestMethodCoverage() {
        return classCoverage.getTestMethodCoverage(currentTestMethodName);
    }

    /**
     * Retrieves the class coverage.
     * 
     * @return
     */
    @Override
    public ClassCoverage getClassCoverage() {
        return classCoverage;
    }

    /**
     * Retrieves the name of the currently executing test method.
     * 
     * @return
     */
    @Override
    public String getCurrentTestMethodName() {
        return currentTestMethodName;
    }

    /**
     * Sets the name of the currently executing test mehod.
     * 
     * @param currentTestName
     */
    @Override
    public void setCurrentTestMethodName(String currentTestName) {
        this.currentTestMethodName = currentTestName;
    }

    @Override
    public String getTestClassName() {
        return testClassName;
    }

    @Override
    public void setTestClassName(String className) {
        this.testClassName = className;
    }

    @Override
    public void setExcludedProcessDefinitionKeys(List<String> excludedProcessDefinitionKeys) {
        this.excludedProcessDefinitionKeys = excludedProcessDefinitionKeys;
    }

    private boolean isExcluded(CoveredElement coveredElement) {
        if (excludedProcessDefinitionKeys != null) {
            return excludedProcessDefinitionKeys.contains(coveredElement.getProcessDefinitionKey());
        }
        return false;
    }

}
