package io.holunda.commons.immutables;

import java.util.Date;
import java.util.UUID;
import javax.activation.MimeType;
import org.assertj.core.util.DateUtil;
import org.camunda.bpm.engine.ActivityTypes;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.impl.event.EventType;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.TransitionInstance;
import org.camunda.bpm.engine.task.Attachment;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.IdentityLinkType;

public enum _Fixtures {
  ;

  public static final String ACTIVITY_ID = "activityId";
  public static final String ACTIVITY_NAME = "Activity Name";
  public static final String BATCH_TYPE = "batchType";
  public static final String CASE_DEFINITION_KEY = "theCase";
  public static final String CASE_DEFINITION_ID = CASE_DEFINITION_KEY + ":1:1";
  public static final String CASE_INSTANCE_ID = uuid();
  public static final Date DATE_NOW = DateUtil.now();
  public static final Date DATE_TOMORROW = DateUtil.tomorrow();

  public static final String DEPLOYMENT_ID = uuid();
  public static final String EXECUTION_ID = uuid();
  public static final String GROUP_ID = "group-y";
  public static final String JOB_ID = uuid();
  public static final String JOB_DEFINITION_ID = uuid();
  public static final String PROCESS_DEFINITION_KEY = "theProcess";
  public static final String PROCESS_DEFINITION_ID = PROCESS_DEFINITION_KEY + ":1:1";
  public static final String PROCESS_INSTANCE_ID = uuid();
  public static final String TASK_ID = uuid();
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
      throw CamundaImmutables.UNMODIFIABLE;
    }

    @Override
    public String getDescription() {
      return "description";
    }

    @Override
    public void setDescription(String description) {
      throw CamundaImmutables.UNMODIFIABLE;
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
    String id = uuid();

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
}
