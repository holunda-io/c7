package io.holunda.camunda.bpm.data

import io.holunda.camunda.bpm.data.builder.VariableMapBuilder
import io.holunda.camunda.bpm.data.writer.CaseServiceVariableWriter
import io.holunda.camunda.bpm.data.writer.GlobalVariableWriter
import io.holunda.camunda.bpm.data.writer.RuntimeServiceVariableWriter
import io.holunda.camunda.bpm.data.writer.TaskServiceVariableWriter
import io.holunda.camunda.bpm.data.writer.VariableMapWriter
import io.holunda.camunda.bpm.data.writer.VariableScopeWriter
import io.holunda.camunda.bpm.data.writer.VariableWriter
import org.camunda.bpm.engine.CaseService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.delegate.VariableScope
import org.camunda.bpm.engine.variable.VariableMap

/**
 * Access creators for writers.
 */
object Writers {

  /**
   * Writers for Camunda 7.
   */
  object C7 {

    /**
     * Creates a new variable map builder.
     *
     * @return new writer with empty variable map.
     */
    @JvmStatic
    fun builder(): VariableMapBuilder {
      return VariableMapBuilder()
    }


    /**
     * Creates a new variable map builder.
     *
     * @param variables pre-created, potentially non-empty variables.
     * @return new writer
     */
    @JvmStatic
    fun writer(variables: VariableMap): GlobalVariableWriter<*> {
      return VariableMapWriter(variables)
    }

    /**
     * Creates a new variable scope writer.
     *
     * @param variableScope scope to work on (delegate execution or delegate task).
     * @return new writer working on provided variable scope.
     */
    @JvmStatic
    fun writer(variableScope: VariableScope): VariableWriter<*> {
      return VariableScopeWriter(variableScope)
    }

    /**
     * Creates a new execution variable writer.
     *
     * @param runtimeService runtime service to use.
     * @param executionId    id of the execution.
     * @return new writer working on provided process execution.
     */
    @JvmStatic
    fun writer(runtimeService: RuntimeService, executionId: String): VariableWriter<*> {
      return RuntimeServiceVariableWriter(runtimeService, executionId)
    }

    /**
     * Creates a new task variable writer.
     *
     * @param taskService task service to use.
     * @param taskId      task id.
     * @return new writer working on provided user task.
     */
    @JvmStatic
    fun writer(taskService: TaskService, taskId: String): VariableWriter<*> {
      return TaskServiceVariableWriter(taskService, taskId)
    }

    /**
     * Creates a new caseExecution variable writer.
     *
     * @param caseService     task service to use.
     * @param caseExecutionId caseExecution id.
     * @return new writer working on provided user task.
     */
    @JvmStatic
    fun writer(caseService: CaseService, caseExecutionId: String): VariableWriter<*> {
      return CaseServiceVariableWriter(caseService, caseExecutionId)
    }
  }

}
