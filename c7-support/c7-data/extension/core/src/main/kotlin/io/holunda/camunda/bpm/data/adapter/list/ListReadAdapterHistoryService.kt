package io.holunda.camunda.bpm.data.adapter.list

import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.externaltask.LockedExternalTask
import org.camunda.bpm.engine.variable.Variables
import java.util.*

/**
 * Read adapter for history service.
 *
 * @param [T] type of value.
 */
class ListReadAdapterHistoryService<T>(
  private val historyService: HistoryService,
  private val executionId: String,
  variableName: String,
  memberClazz: Class<T>
) : AbstractListReadWriteAdapter<T>(variableName, memberClazz) {



  override fun getOptional(): Optional<List<T>> {
    return Optional.ofNullable(
      getOrNull(
       historyService.createHistoricVariableInstanceQuery()
         .executionIdIn(executionId)
         .variableName(variableName)
         .singleResult().value
      )
    )
  }

  override fun set(value: List<T>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on an historic execution")
  }

  override fun setLocal(value: List<T>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on an historic execution")
  }

  override fun getLocal(): List<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun getLocalOptional(): Optional<List<T>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun getLocalOrDefault(defaultValue: List<T>): List<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun getLocalOrNull(): List<T> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on an historic execution")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on an historic execution")
  }
}
