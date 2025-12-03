package io.holunda.camunda.bpm.data.adapter.basic

import org.camunda.bpm.engine.HistoryService
import java.util.*

/**
 * Read-write adapter for history service.
 *
 * @param [T] type of value.
 */
class ReadAdapterHistoricTaskService<T : Any?>(
  private val historyService: HistoryService,
  private val taskId: String,
  variableName: String,
  clazz: Class<T>
) : AbstractBasicReadWriteAdapter<T>(variableName, clazz) {


  override fun set(value: T, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on historic task")
  }

  override fun setLocal(value: T, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on historic task")
  }

  override fun getOptional(): Optional<T> {
    @Suppress("UNCHECKED_CAST")
    return Optional.ofNullable(
      getOrNull(
        historyService.createHistoricVariableInstanceQuery().taskIdIn(taskId)
          .variableName(variableName).singleResult().value
      )
    ) as Optional<T>
  }

  override fun getLocal(): T {
    throw UnsupportedOperationException("Can't get a local variable on historic task")
  }

  override fun getLocalOptional(): Optional<T> {
    throw UnsupportedOperationException("Can't get a local variable on historic task")
  }

  override fun getLocalOrDefault(defaultValue: T): T {
    throw UnsupportedOperationException("Can't get a local variable on historic task")
  }

  override fun getLocalOrNull(): T {
    throw UnsupportedOperationException("Can't get a local variable on historic task")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on historic task")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on historic task")
  }
}
