package io.holunda.commons.immutables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.holunda.commons.immutables.batch.ImmutableBatch;
import io.holunda.commons.immutables.identity.ImmutableGroup;
import io.holunda.commons.immutables.identity.ImmutableTenant;
import io.holunda.commons.immutables.identity.ImmutableUser;
import io.holunda.commons.immutables.repository.ImmutableCaseDefinition;
import io.holunda.commons.immutables.runtime.*;
import io.holunda.commons.immutables.task.ImmutableAttachment;
import io.holunda.commons.immutables.task.ImmutableComment;
import io.holunda.commons.immutables.task.ImmutableIdentityLink;
import io.holunda.commons.immutables.task.ImmutableTask;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.calendar.DateTimeUtil;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.*;
import org.camunda.bpm.engine.task.Attachment;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.function.Supplier;

public final class CamundaImmutables {

  public static final UnsupportedOperationException UNMODIFIABLE = new UnsupportedOperationException("field is unmodifiable");
  static Supplier<Date> nowSupplier = () -> DateTimeUtil.now().toDate();

  public static ImmutableActivityInstance activityInstance(final ActivityInstance activityInstance) {
    return ImmutableActivityInstance.builder().from(activityInstance).build();
  }

  public static ImmutableAttachment attachment(Attachment attachment) {
    return ImmutableAttachment.builder().from(attachment).build();
  }

  public static ImmutableBatch batch(Batch batch) {
    return ImmutableBatch.builder().from(batch).build();
  }

  public static ImmutableCaseDefinition caseDefinition(CaseDefinition caseDefinition) {
    return ImmutableCaseDefinition.builder().from(caseDefinition).build();
  }

  public static ImmutableCaseExecution caseExecution(final CaseExecution caseExecution) {
    return ImmutableCaseExecution.builder().from(caseExecution).build();
  }

  public static ImmutableCaseInstance caseInstance(final CaseInstance caseInstance) {
    return ImmutableCaseInstance.builder().from(caseInstance).build();
  }

  public static ImmutableComment comment(Comment comment) {
    return ImmutableComment.builder().from(comment).build();
  }

  public static ImmutableEventSubscription eventSubscription(final EventSubscription eventSubscription) {
    return ImmutableEventSubscription.builder().from(eventSubscription).build();
  }

  public static ImmutableExecution execution(final Execution execution) {
    return ImmutableExecution.builder().from(execution).build();
  }

  public static ImmutableGroup group(final Group group) {
    return ImmutableGroup.builder().from(group).build();
  }

  public static ImmutableIdentityLink identityLink(final IdentityLink identityLink) {
    return ImmutableIdentityLink.builder().from(identityLink).build();
  }

  public static ImmutableIncident incident(final Incident incident) {
    return ImmutableIncident.builder().from(incident).build();
  }

  public static ImmutableJob job(final Job job) {
    return ImmutableJob.builder().from(job).build();
  }

  public static ImmutableProcessElementInstance processElementInstance(final ProcessElementInstance processElementInstance) {
    return ImmutableProcessElementInstance.builder().from(processElementInstance).build();
  }

  public static ImmutableProcessInstance processInstance(final ProcessInstanceWithVariables processInstance) {
    return ImmutableProcessInstance.builder().from(processInstance).build();
  }

  public static ImmutableProcessInstance processInstance(final ProcessInstance processInstance) {
    return ImmutableProcessInstance.builder().from(processInstance).build();
  }

  public static ImmutableTask task(final Task task) {
    return ImmutableTask.builder().from(task).build();
  }

  public static ImmutableTenant tenant(final Tenant tenant) {
    return ImmutableTenant.builder().from(tenant).build();
  }

  public static ImmutableUser user(final User user) {
    return ImmutableUser.builder().from(user).build();
  }

  private CamundaImmutables() {
    // do not instantiate
  }


  public interface CurrentTimestamp {

    @NotNull
    @JsonIgnore
    default Supplier<Date> getNow() {
      return nowSupplier;
    }
  }
}
