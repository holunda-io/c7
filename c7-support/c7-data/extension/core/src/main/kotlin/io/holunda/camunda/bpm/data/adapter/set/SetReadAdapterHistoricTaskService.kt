package io.holunda.camunda.bpm.data.adapter.set

import org.camunda.bpm.engine.HistoryService
import java.util.*

/**
 * Read adapter for history service.
 *
 * @param [T] type of value.
 * @param variableName name of the variable.
 * @param memberClazz class of the variable.
 */
class SetReadAdapterHistoricTaskService<T>(
  private val historyService: HistoryService,
  private val taskId: String,
  variableName: String, memberClazz: Class<T>
) : AbstractSetReadWriteAdapter<T>(variableName, memberClazz) {



  override fun getOptional(): Optional<Set<T>> {
    return Optional.ofNullable(
      getOrNull(
        historyService.createHistoricVariableInstanceQuery()
          .taskIdIn(taskId)
          .variableName(variableName)
          .singleResult().value
      )
    )
  }

  override fun set(value: Set<T>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on an historic task")
  }

  override fun setLocal(value: Set<T>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on an historic task")
  }

  override fun getLocal(): Set<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOptional(): Optional<Set<T>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getOrDefault(defaultValue: Set<T>): Set<T> {
    return getOptional().orElse(defaultValue)
  }

  override fun getLocalOrDefault(defaultValue: Set<T>): Set<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOrNull(): Set<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on an historic task")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on an historic task")
  }


}
