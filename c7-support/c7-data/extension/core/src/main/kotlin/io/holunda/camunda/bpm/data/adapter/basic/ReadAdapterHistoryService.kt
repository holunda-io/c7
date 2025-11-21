package io.holunda.camunda.bpm.data.adapter.basic

import org.camunda.bpm.engine.HistoryService
import java.util.*

/**
 * Read-write adapter for history service.
 *
 * @param [T] type of value.
 */
class ReadAdapterHistoryService<T : Any?>(
  private val historyService: HistoryService,
  private val executionId: String,
  variableName: String,
  clazz: Class<T>
) : AbstractBasicReadWriteAdapter<T>(variableName, clazz) {


  override fun set(value: T, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on historic execution")
  }

  override fun setLocal(value: T, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on historic execution")
  }

  override fun getOptional(): Optional<T> {
    @Suppress("UNCHECKED_CAST")
    return Optional.ofNullable(
      getOrNull(
        historyService.createHistoricVariableInstanceQuery().executionIdIn(executionId)
          .variableName(variableName).singleResult().value
      )
    ) as Optional<T>
  }

  override fun getLocal(): T {
    throw UnsupportedOperationException("Can't get a local variable on historic execution")
  }

  override fun getLocalOptional(): Optional<T> {
    throw UnsupportedOperationException("Can't get a local variable on historic execution")
  }

  override fun getLocalOrDefault(defaultValue: T): T {
    throw UnsupportedOperationException("Can't get a local variable on historic execution")
  }

  override fun getLocalOrNull(): T {
    throw UnsupportedOperationException("Can't get a local variable on historic execution")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on historic execution")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on historic execution")
  }
}
