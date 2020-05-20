package io.holunda.commons.immutables;

import java.util.UUID;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.IdentityLinkType;

public enum _Fixtures {
  ;

  public static final String ACTIVITY_ID = "activityId";
  public static final String ACTIVITY_NAME = "Activity Name";
  public static final String BATCH_TYPE = "batchType";
  public static final String GROUP_ID = "group-y";
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
}
