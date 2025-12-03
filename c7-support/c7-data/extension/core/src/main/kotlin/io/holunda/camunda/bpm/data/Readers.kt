package io.holunda.camunda.bpm.data

import io.holunda.camunda.bpm.data.reader.*
import org.camunda.bpm.engine.CaseService
import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.delegate.VariableScope
import org.camunda.bpm.engine.externaltask.LockedExternalTask
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables
import org.camunda.bpm.engine.variable.VariableMap

/**
 * Access creator for readers.
 */
object Readers {

  /**
   * Readers for Camunda 7.
   */
  object C7 {
    /**
     * Creates a new task variable reader.
     *
     * @param taskService the Camunda task service
     * @param taskId      the id of the task to use
     * @return variable reader working on task
     */
    @JvmStatic
    fun reader(taskService: TaskService, taskId: String): VariableReader {
      return TaskServiceVariableReader(taskService, taskId)
    }

    /**
     * Creates a new execution variable reader.
     *
     * @param runtimeService the Camunda runtime service
     * @param executionId    the executionId to use
     * @return variable reader working on execution
     */
    @JvmStatic
    fun reader(runtimeService: RuntimeService, executionId: String): VariableReader {
      return RuntimeServiceVariableReader(runtimeService, executionId)
    }

    /**
     * Creates a new execution variable reader.
     *
     * @param historyService the Camunda history service
     * @param executionId    the executionId to use
     * @return variable reader working on execution
     */
    @JvmStatic
    fun reader(historyService: HistoryService, executionId: String): VariableReader {
      return HistoryServiceVariableReader(historyService, executionId)
    }

    /**
     * Creates a new execution variable reader.
     *
     * @param historyService the Camunda history service
     * @param taskId      the id of the task to use
     * @return variable reader working on execution
     */
    @JvmStatic
    fun taskReader(historyService: HistoryService, taskId: String): VariableReader {
      return HistoricTaskServiceVariableReader(historyService, taskId)
    }



    /**
     * Creates a new execution variable reader.
     *
     * @param caseService     the Camunda case service
     * @param caseExecutionId the caseExecutionId to use
     * @return variable reader working on execution
     */
    @JvmStatic
    fun reader(caseService: CaseService, caseExecutionId: String): VariableReader {
      return CaseServiceVariableReader(caseService, caseExecutionId)
    }

    /**
     * Creates a new variableScope variable reader.
     *
     * @param variableScope the variable scope to use (DelegateExecution, DelegateTask)
     * @return variable reader working on variableScope
     */
    @JvmStatic
    fun reader(variableScope: VariableScope): VariableReader {
      return VariableScopeReader(variableScope)
    }

    /**
     * Creates a new variableMap variable reader.
     *
     * @param variableMap the variableMap to use
     * @return variable reader working on variableMap
     */
    @JvmStatic
    fun reader(variableMap: VariableMap): VariableReader {
      return VariableMapReader(variableMap)
    }

    /**
     * Creates a new processInstance variable reader.
     *
     * @see .reader
     * @param processInstance the processInstance with variables to read from
     * @return variable reader working on the variableMap provided by instance
     */
    @JvmStatic
    fun reader(processInstance: ProcessInstanceWithVariables): VariableReader {
      return reader(processInstance.variables)
    }

    /**
     * Creates a new extern variable reader.
     *
     * @param lockedExternalTask the external tasks to use
     * @return variable reader working on external task
     */
    @JvmStatic
    fun reader(lockedExternalTask: LockedExternalTask): VariableReader {
      return LockedExternalTaskReader(lockedExternalTask)
    }

  }
}
