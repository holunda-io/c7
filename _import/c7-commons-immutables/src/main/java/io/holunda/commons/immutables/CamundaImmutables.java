package io.holunda.commons.immutables;

import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.task.Task;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;
import org.jetbrains.annotations.Nullable;

public final class CamundaImmutables {

  public static ImmutableBatch batch(Batch batch) {
    return ImmutableBatch.builder().from(batch).build();
  }

  public static ImmutableEventSubscription eventSubscription(final EventSubscription eventSubscription) {
    return ImmutableEventSubscription.builder().from(eventSubscription).build();
  }

  public static ImmutableExecution execution(final Execution execution) {
    return ImmutableExecution.builder().from(execution).build();
  }

  public static ImmutableJob job(final Job job) {
    return ImmutableJob.builder().from(job).build();
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

  enum ImmutablesConfiguration {
    ;

    @Value.Style(
      // Detect names starting with underscore
      typeAbstract = "_*",
      // Make generated public, leave underscored as package private
      visibility = ImplementationVisibility.PUBLIC,
      // Seems unnecessary to have builder or superfluous copy method
      defaults = @Value.Immutable(
        builder = true, copy = true
      )
    )
    public @interface CamundaPojoStyle {
      // empty
    }

  }

  enum Facets {
    ;

    static final UnsupportedOperationException UNMODIFIABLE = new UnsupportedOperationException("field is unmodifiable");

    interface WithTenantId {

      @Nullable
      String getTenantId();
    }

    interface WithBusinessKey {

      @Nullable
      String getBusinessKey();
    }

  }

  private CamundaImmutables() {
    // do not instantiate
  }
}
