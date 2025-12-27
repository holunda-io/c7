package io.holunda.camunda.bpm.data.adapter.listofmaps

import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.externaltask.LockedExternalTask
import org.camunda.bpm.engine.variable.Variables
import java.util.*
import kotlin.collections.Map

/**
 * Read adapter for history service.
 *
 * @param [K] key type.
 * @param [V] value type.
 */
class ListOfMapsReadAdapterHistoryService<K, V>(
  private val historyService: HistoryService,
  private val executionId: String,
  variableName: String,
  keyClazz: Class<K>,
  valueClazz: Class<V>
) : AbstractListOfMapsReadWriteAdapter<K, V>(variableName, keyClazz, valueClazz) {



  override fun getOptional(): Optional<List<Map<K, V>>> {
    return Optional.ofNullable(
      getOrNull(
        historyService.createHistoricVariableInstanceQuery()
          .executionIdIn(executionId)
          .variableName(variableName)
          .singleResult().value
      )
    )
  }

  override fun set(value: List<Map<K, V>>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on an historic execution")
  }

  override fun setLocal(value: List<Map<K, V>>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on an historic execution")
  }

  override fun getLocal(): List<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun getLocalOptional(): Optional<List<Map<K, V>>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun getLocalOrDefault(defaultValue: List<Map<K, V>>): List<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun getLocalOrNull(): List<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic execution")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on an historic execution")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on an historic execution")
  }
}
