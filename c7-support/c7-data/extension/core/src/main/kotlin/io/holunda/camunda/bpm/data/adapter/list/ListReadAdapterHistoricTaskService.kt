package io.holunda.camunda.bpm.data.adapter.list

import org.camunda.bpm.engine.HistoryService
import java.util.*

/**
 * Read adapter for history service.
 *
 * @param [T] type of value.
 */
class ListReadAdapterHistoricTaskService<T>(
  private val historyService: HistoryService,
  private val taskId: String,
  variableName: String,
  memberClazz: Class<T>
) : AbstractListReadWriteAdapter<T>(variableName, memberClazz) {



  override fun getOptional(): Optional<List<T>> {
    return Optional.ofNullable(
      getOrNull(
       historyService.createHistoricVariableInstanceQuery()
         .taskIdIn(taskId)
         .variableName(variableName)
         .singleResult().value
      )
    )
  }

  override fun set(value: List<T>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on an historic task")
  }

  override fun setLocal(value: List<T>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on an historic task")
  }

  override fun getLocal(): List<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOptional(): Optional<List<T>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOrDefault(defaultValue: List<T>): List<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOrNull(): List<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on an historic task")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on an historic task")
  }
}
