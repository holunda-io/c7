package org.camunda.bpm.engine.impl.jobexecutor;

/**
 * Job executor should be not referenced directly from API.
 * Currently, it is referenced via JavaDoc from {@link org.camunda.bpm.engine.ManagementService} and {@link org.camunda.bpm.engine.repository.DeploymentHandler}.
 */
public interface JobExecutor {
}
