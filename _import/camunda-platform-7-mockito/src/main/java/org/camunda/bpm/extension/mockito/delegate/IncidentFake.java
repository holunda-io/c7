package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.runtime.Incident;

import java.util.Date;
import java.util.UUID;

public class IncidentFake implements Incident {

  private final String id = UUID.randomUUID().toString();
  private final Date timestamp = new Date();

  private final DelegateExecutionFake execution;
  private final String type;
  private final String message;
  private final String configuration;
  private final String jobDefinitionId;
  private final String historyConfiguration;
  private final String failedActivityId;
  private final String annotation;

  public IncidentFake(DelegateExecutionFake execution, String type, String configuration, String message, String jobDefinitionId, String historyConfiguration, String failedActivityId, String annotation) {
    this.execution = execution;
    this.type = type;
    this.configuration = configuration;
    this.message = message;
    this.jobDefinitionId = jobDefinitionId;
    this.historyConfiguration = historyConfiguration;
    this.failedActivityId = failedActivityId;
    this.annotation = annotation;
  }


  @Override
  public String getId() {
    return id;
  }

  @Override
  public Date getIncidentTimestamp() {
    return timestamp;
  }

  @Override
  public String getIncidentType() {
    return type;
  }

  @Override
  public String getIncidentMessage() {
    return message;
  }

  @Override
  public String getExecutionId() {
    return execution.getId();
  }

  @Override
  public String getActivityId() {
    return execution.getCurrentActivityId();
  }

  @Override
  public String getFailedActivityId() {
    return failedActivityId;
  }

  @Override
  public String getProcessInstanceId() {
    return execution.getProcessInstanceId();
  }

  @Override
  public String getProcessDefinitionId() {
    return execution.getProcessDefinitionId();
  }

  @Override
  public String getCauseIncidentId() {
    return id;
  }

  @Override
  public String getRootCauseIncidentId() {
    return id;
  }

  @Override
  public String getConfiguration() {
    return configuration;
  }

  @Override
  public String getTenantId() {
    return execution.getTenantId();
  }

  @Override
  public String getJobDefinitionId() {
    return jobDefinitionId;
  }

  @Override
  public String getHistoryConfiguration() {
    return historyConfiguration;
  }

  @Override
  public String getAnnotation() {
    return annotation;
  }


  @Override public String toString() {
    return "IncidentFake{" +
      "id='" + id + '\'' +
      ", timestamp=" + timestamp +
      ", execution=" + execution +
      ", type='" + type + '\'' +
      ", message='" + message + '\'' +
      ", configuration='" + configuration + '\'' +
      ", jobDefinitionId='" + jobDefinitionId + '\'' +
      ", historyConfiguration='" + historyConfiguration + '\'' +
      ", failedActivityId='" + failedActivityId + '\'' +
      ", annotation='" + annotation + '\'' +
      '}';
  }
}
