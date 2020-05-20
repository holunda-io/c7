package io.holunda.commons.immutables;

import java.util.Date;
import java.util.function.Supplier;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.impl.calendar.DateTimeUtil;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.task.Task;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

public final class CamundaImmutables {
  static final UnsupportedOperationException UNMODIFIABLE = new UnsupportedOperationException("field is unmodifiable");
  static final Supplier<Date> NOW = () -> DateTimeUtil.now().toDate();

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
      defaultAsDefault = true,
      // Make generated public, leave underscored as package private
      visibility = ImplementationVisibility.PUBLIC,
      // Seems unnecessary to have builder or superfluous copy method
      defaults = @Value.Immutable(
        builder = true, copy = true, prehash = true
      )
    )
    public @interface CamundaPojoStyle {
      // empty
    }
  }

  private CamundaImmutables() {
    // do not instantiate
  }
}
