package io.holunda.commons.immutables;

import org.assertj.core.util.DateUtil;
import org.camunda.bpm.engine.ActivityTypes;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.form.CamundaFormRef;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.event.EventType;
import org.camunda.bpm.engine.impl.form.CamundaFormRefImpl;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.*;
import org.camunda.bpm.engine.task.*;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

import java.util.Date;
import java.util.UUID;

import static io.holunda.commons.immutables.CamundaImmutables.UNMODIFIABLE;

public enum _Fixtures {
  ;

  public static final String ACTIVITY_ID = "activityId";
  public static final String ACTIVITY_NAME = "Activity Name";
  public static final String BATCH_TYPE = "batchType";
  public static final String BUSINESS_KEY = uuid();
  public static final String CASE_DEFINITION_KEY = "theCase";
  public static final String CASE_DEFINITION_NAME = "The Case";
  public static final String CASE_DEFINITION_ID = CASE_DEFINITION_KEY + ":1:1";
  public static final String CASE_EXECUTION_ID = uuid();
  public static final String CASE_INSTANCE_ID = uuid();
  public static final Date DATE_NOW = DateUtil.now();
  public static final Date DATE_TOMORROW = DateUtil.tomorrow();


  public static final String BATCH_ID = uuid();
  public static final String DEPLOYMENT_ID = uuid();
  public static final String ID = uuid();
  public static final String EXECUTION_ID = uuid();
  public static final String GROUP_ID = "group-y";
  public static final String JOB_ID = uuid();
  public static final String JOB_DEFINITION_ID = uuid();
  public static final String PROCESS_DEFINITION_KEY = "theProcess";
  public static final String PROCESS_DEFINITION_NAME = "The Process";
  public static final int PROCESS_DEFINITION_VERSION = 1;
  public static final String PROCESS_DEFINITION_ID = PROCESS_DEFINITION_KEY + ":1:1";
  public static final String PROCESS_INSTANCE_ID = uuid();
  public static final String ROOT_PROCESS_INSTANCE_ID = uuid();
  public static final String TASK_ID = uuid();
  public static final String TASK_DEFINITION_KEY = "theTask";
  public static final String TENANT_ID = "tenant-a";
  public static final String USER_ID = "user-x";

  public static String uuid() {
    return UUID.randomUUID().toString();
  }

  public static final Batch BATCH = new Batch() {
    final String id = uuid();
    final String seedJobDefinitionId = uuid();
    final String monitorJobDefinitionId = uuid();
    final String batchJobDefinitionId = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getType() {
      return BATCH_TYPE;
    }

    @Override
    public int getTotalJobs() {
      return 10;
    }

    @Override
    public int getJobsCreated() {
      return 10;
    }

    @Override
    public int getBatchJobsPerSeed() {
      return 10;
    }

    @Override
    public int getInvocationsPerBatchJob() {
      return 10;
    }

    @Override
    public String getSeedJobDefinitionId() {
      return seedJobDefinitionId;
    }

    @Override
    public String getMonitorJobDefinitionId() {
      return monitorJobDefinitionId;
    }

    @Override
    public String getBatchJobDefinitionId() {
      return batchJobDefinitionId;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public String getCreateUserId() {
      return USER_ID;
    }

    @Override
    public boolean isSuspended() {
      return true;
    }

    @Override
    public Date getStartTime() {
      return new Date();
    }

    @Override
    public Date getExecutionStartTime() {
      return new Date();
    }
  };

  public static final IdentityLink IDENTITY_LINK = new IdentityLink() {
    final String id = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getType() {
      return IdentityLinkType.ASSIGNEE;
    }

    @Override
    public String getUserId() {
      return USER_ID;
    }

    @Override
    public String getGroupId() {
      return GROUP_ID;
    }

    @Override
    public String getTaskId() {
      return TASK_ID;
    }

    @Override
    public String getProcessDefId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }
  };

  public static final Job JOB = new Job() {

    @Override
    public String getId() {
      return JOB_ID;
    }

    @Override
    public Date getDuedate() {
      return DATE_TOMORROW;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getProcessDefinitionId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getProcessDefinitionKey() {
      return PROCESS_DEFINITION_KEY;
    }

    @Override
    public String getExecutionId() {
      return EXECUTION_ID;
    }

    @Override
    public int getRetries() {
      return 10;
    }

    @Override
    public String getExceptionMessage() {
      return "error";
    }

    @Override
    public String getFailedActivityId() {
      return "failedActivityId";
    }

    @Override
    public String getDeploymentId() {
      return DEPLOYMENT_ID;
    }

    @Override
    public String getJobDefinitionId() {
      return JOB_DEFINITION_ID;
    }

    @Override
    public boolean isSuspended() {
      return true;
    }

    @Override
    public long getPriority() {
      return 200;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public Date getCreateTime() {
      return DATE_NOW;
    }

    @Override
    public String getRootProcessInstanceId() {
      return "";
    }

    @Override
    public String getBatchId() {
      return BATCH_ID;
    }
  };

  public static final EventSubscription EVENT_SUBSCRIPTION = new EventSubscription() {
    String id = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getEventType() {
      return EventType.MESSAGE.name();
    }

    @Override
    public String getEventName() {
      return "MY_EVENT";
    }

    @Override
    public String getExecutionId() {
      return EXECUTION_ID;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getActivityId() {
      return ACTIVITY_ID;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public Date getCreated() {
      return DATE_NOW;
    }
  };

  public static final ActivityInstance ACTIVITY_INSTANCE = new ActivityInstance() {
    String id = uuid();

    @Override
    public String getActivityId() {
      return ACTIVITY_ID;
    }

    @Override
    public String getActivityName() {
      return ACTIVITY_NAME;
    }

    @Override
    public String getActivityType() {
      return ActivityTypes.TASK_USER_TASK;
    }

    @Override
    public ActivityInstance[] getChildActivityInstances() {
      return new ActivityInstance[0];
    }

    @Override
    public TransitionInstance[] getChildTransitionInstances() {
      return new TransitionInstance[0];
    }

    @Override
    public String[] getExecutionIds() {
      return new String[0];
    }

    @Override
    public ActivityInstance[] getActivityInstances(String activityId) {
      return new ActivityInstance[0];
    }

    @Override
    public TransitionInstance[] getTransitionInstances(String activityId) {
      return new TransitionInstance[0];
    }

    @Override
    public String[] getIncidentIds() {
      return new String[0];
    }

    @Override
    public Incident[] getIncidents() {
      return new Incident[0];
    }

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getParentActivityInstanceId() {
      return null;
    }

    @Override
    public String getProcessDefinitionId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }
  };

  public static final Attachment ATTACHMENT = new Attachment() {
    String id = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getName() {
      return "name";
    }

    @Override
    public void setName(String name) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getDescription() {
      return "description";
    }

    @Override
    public void setDescription(String description) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getType() {
      return "application/json";
    }

    @Override
    public String getTaskId() {
      return TASK_ID;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getUrl() {
      return "http://localhost";
    }

    @Override
    public Date getCreateTime() {
      return DATE_NOW;
    }

    @Override
    public String getRootProcessInstanceId() {
      return null;
    }

    @Override
    public Date getRemovalTime() {
      return DATE_TOMORROW;
    }
  };

  public static final CaseExecution CASE_EXECUTION = new CaseExecution() {
    final String id = CASE_EXECUTION_ID;

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getCaseInstanceId() {
      return CASE_INSTANCE_ID;
    }

    @Override
    public String getCaseDefinitionId() {
      return CASE_DEFINITION_ID;
    }

    @Override
    public String getActivityId() {
      return ACTIVITY_ID;
    }

    @Override
    public String getActivityName() {
      return ACTIVITY_NAME;
    }

    @Override
    public String getActivityType() {
      return ActivityTypes.TASK_USER_TASK;
    }

    @Override
    public String getActivityDescription() {
      return "the description";
    }

    @Override
    public String getParentId() {
      return null;
    }

    @Override
    public boolean isRequired() {
      return true;
    }

    @Override
    public boolean isAvailable() {
      return true;
    }

    @Override
    public boolean isActive() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    @Override
    public boolean isDisabled() {
      return true;
    }

    @Override
    public boolean isTerminated() {
      return true;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }
  };

  public static final CaseInstance CASE_INSTANCE = new CaseInstance() {

    final String id = CASE_INSTANCE_ID;

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getCaseInstanceId() {
      return CASE_INSTANCE_ID;
    }

    @Override
    public String getCaseDefinitionId() {
      return CASE_DEFINITION_ID;
    }

    @Override
    public String getActivityId() {
      return ACTIVITY_ID;
    }

    @Override
    public String getActivityName() {
      return ACTIVITY_NAME;
    }

    @Override
    public String getActivityType() {
      return ActivityTypes.TASK_USER_TASK;
    }

    @Override
    public String getActivityDescription() {
      return "the description";
    }

    @Override
    public String getParentId() {
      return null;
    }

    @Override
    public boolean isRequired() {
      return true;
    }

    @Override
    public boolean isAvailable() {
      return true;
    }

    @Override
    public boolean isActive() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    @Override
    public boolean isDisabled() {
      return true;
    }

    @Override
    public boolean isTerminated() {
      return true;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public String getBusinessKey() {
      return BUSINESS_KEY;
    }

    @Override
    public boolean isCompleted() {
      return true;
    }
  };

  public static final ProcessInstanceWithVariables PROCESS_INSTANCE = new ProcessInstanceWithVariables() {

    final String id = uuid();

    @Override
    public VariableMap getVariables() {
      return Variables.putValue("foo", "bar");
    }

    @Override
    public String getProcessDefinitionId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getBusinessKey() {
      return BUSINESS_KEY;
    }

    @Override
    public String getRootProcessInstanceId() {
      return null;
    }

    @Override
    public String getCaseInstanceId() {
      return CASE_INSTANCE_ID;
    }

    @Override
    public boolean isSuspended() {
      return true;
    }

    @Override
    public String getId() {
      return id;
    }

    @Override
    public boolean isEnded() {
      return true;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public String getProcessDefinitionKey() {
      return PROCESS_DEFINITION_KEY;
    }
  };

  public static final Comment COMMENT = new Comment() {
    final String id = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getUserId() {
      return USER_ID;
    }

    @Override
    public Date getTime() {
      return DATE_NOW;
    }

    @Override
    public String getTaskId() {
      return TASK_ID;
    }

    @Override
    public String getRootProcessInstanceId() {
      return null;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getFullMessage() {
      return "the message";
    }

    @Override
    public Date getRemovalTime() {
      return DATE_TOMORROW;
    }
  };

  public static final Task TASK = new Task() {
    @Override
    public String getId() {
      return TASK_ID;
    }

    @Override
    public String getName() {
      return "the task";
    }

    @Override
    public void setName(String name) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getDescription() {
      return "the description";
    }

    @Override
    public void setDescription(String description) {
      throw UNMODIFIABLE;
    }

    @Override
    public int getPriority() {
      return PRIORITY_MAXIMUM;
    }

    @Override
    public void setPriority(int priority) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getOwner() {
      return USER_ID;
    }

    @Override
    public void setOwner(String owner) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getAssignee() {
      return USER_ID;
    }

    @Override
    public void setAssignee(String assignee) {
      throw UNMODIFIABLE;
    }

    @Override
    public DelegationState getDelegationState() {
      return DelegationState.PENDING;
    }

    @Override
    public void setDelegationState(DelegationState delegationState) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getExecutionId() {
      return EXECUTION_ID;
    }

    @Override
    public String getProcessDefinitionId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getCaseInstanceId() {
      return CASE_INSTANCE_ID;
    }

    @Override
    public void setCaseInstanceId(String caseInstanceId) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getCaseExecutionId() {
      return CASE_EXECUTION.getId();
    }

    @Override
    public String getCaseDefinitionId() {
      return CASE_DEFINITION_ID;
    }

    @Override
    public Date getCreateTime() {
      return DATE_NOW;
    }

    @Override
    public Date getLastUpdated() {
      return DATE_NOW;
    }

    @Override
    public String getTaskDefinitionKey() {
      return TASK_DEFINITION_KEY;
    }

    @Override
    public Date getDueDate() {
      return DATE_TOMORROW;
    }

    @Override
    public void setDueDate(Date dueDate) {
      throw UNMODIFIABLE;
    }

    @Override
    public Date getFollowUpDate() {
      return DATE_TOMORROW;
    }

    @Override
    public void setFollowUpDate(Date followUpDate) {
      throw UNMODIFIABLE;
    }

    @Override
    public void delegate(String userId) {
      throw UNMODIFIABLE;
    }

    @Override
    public void setParentTaskId(String parentTaskId) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getParentTaskId() {
      return null;
    }

    @Override
    public boolean isSuspended() {
      return true;
    }

    @Override
    public String getFormKey() {
      return "form/key";
    }

    @Override
    public CamundaFormRef getCamundaFormRef() {
      CamundaFormRefImpl formRef = new CamundaFormRefImpl("key", "binding");
      formRef.setVersion(1);
      return formRef;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public void setTenantId(String tenantId) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getTaskState() {
      return "";
    }

    @Override
    public void setTaskState(String taskState) {

    }

    @Override
    public boolean hasAttachment() {
      return false;
    }

    @Override
    public boolean hasComment() {
      return false;
    }
  };

  public static final Execution EXECUTION = new Execution() {
    @Override
    public String getId() {
      return EXECUTION_ID;
    }

    @Override
    public boolean isSuspended() {
      return true;
    }

    @Override
    public boolean isEnded() {
      return true;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public String getProcessDefinitionKey() {
      return PROCESS_DEFINITION_KEY;
    }
  };

  public static final ProcessElementInstance PROCESS_ELEMENT_INSTANCE = new ProcessElementInstance() {
    String id = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getParentActivityInstanceId() {
      return ACTIVITY_INSTANCE.getId();
    }

    @Override
    public String getProcessDefinitionId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }
  };

  public static final Incident INCIDENT = new Incident() {
    final String id = uuid();
    final String rootId = uuid();
    final String causeId = uuid();

    @Override
    public String getId() {
      return id;
    }

    @Override
    public Date getIncidentTimestamp() {
      return DATE_NOW;
    }

    @Override
    public String getIncidentType() {
      return Incident.FAILED_JOB_HANDLER_TYPE;
    }

    @Override
    public String getIncidentMessage() {
      return "the message";
    }

    @Override
    public String getExecutionId() {
      return EXECUTION_ID;
    }

    @Override
    public String getActivityId() {
      return ACTIVITY_ID;
    }

    @Override
    public String getFailedActivityId() {
      return "failedActivityId";
    }

    @Override
    public String getProcessInstanceId() {
      return PROCESS_INSTANCE_ID;
    }

    @Override
    public String getProcessDefinitionId() {
      return PROCESS_DEFINITION_ID;
    }

    @Override
    public String getCauseIncidentId() {
      return causeId;
    }

    @Override
    public String getRootCauseIncidentId() {
      return rootId;
    }

    @Override
    public String getConfiguration() {
      return "the-configuration";
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public String getJobDefinitionId() {
      return "job-definition";
    }

    @Override
    public String getHistoryConfiguration() {
      return "historic-configuration";
    }

    @Override
    public String getAnnotation() {
      return "annotation";
    }
  };

  public static final User USER = new User() {
    @Override
    public String getId() {
      return USER_ID;
    }

    @Override
    public void setId(String id) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getFirstName() {
      return "Dagobert";
    }

    @Override
    public void setFirstName(String firstName) {
      throw UNMODIFIABLE;
    }

    @Override
    public void setLastName(String lastName) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getLastName() {
      return "Duck";
    }

    @Override
    public void setEmail(String email) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getEmail() {
      return "dagobert@duck.eh";
    }

    @Override
    public String getPassword() {
      return "password";
    }

    @Override
    public void setPassword(String password) {
      throw UNMODIFIABLE;
    }
  };

  public static final Group GROUP = new Group() {
    @Override
    public String getId() {
      return GROUP_ID;
    }

    @Override
    public void setId(String id) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getName() {
      return "Name of the Group";
    }

    @Override
    public void setName(String name) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getType() {
      return "some type";
    }

    @Override
    public void setType(String string) {
      throw UNMODIFIABLE;
    }
  };

  public static final Tenant TENANT = new Tenant() {
    @Override
    public String getId() {
      return TENANT_ID;
    }

    @Override
    public void setId(String id) {
      throw UNMODIFIABLE;
    }

    @Override
    public String getName() {
      return "tenant name";
    }

    @Override
    public void setName(String name) {
      throw UNMODIFIABLE;
    }
  };

  public static final CaseDefinition CASE_DEFINITION = new CaseDefinition() {
    @Override
    public String getId() {
      return CASE_DEFINITION_ID;
    }

    @Override
    public String getCategory() {
      return "category";
    }

    @Override
    public String getName() {
      return "CaseDefinition";
    }

    @Override
    public String getKey() {
      return CASE_DEFINITION_KEY;
    }

    @Override
    public int getVersion() {
      return 1;
    }

    @Override
    public String getResourceName() {
      return CASE_DEFINITION_KEY + ".cmmn";
    }

    @Override
    public String getDeploymentId() {
      return DEPLOYMENT_ID;
    }

    @Override
    public String getDiagramResourceName() {
      return "file/" + getResourceName();
    }

    @Override
    public String getTenantId() {
      return TENANT_ID;
    }

    @Override
    public Integer getHistoryTimeToLive() {
      return 19;
    }
  };
}
