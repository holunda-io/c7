package io.holunda.camunda.bpm.data.adapter.map

import org.camunda.bpm.engine.HistoryService
import java.util.*

/**
 * Read adapter for history service
 *
 * @param [K] type of key.
 * @param [V] type of value.
 * @param variableName name of the variable.
 */
class MapReadAdapterHistoryService<K, V>(
  private val historyService: HistoryService,
  private val executionId: String,
  variableName: String,
  keyClazz: Class<K>,
  valueClazz: Class<V>
) : AbstractMapReadWriteAdapter<K, V>(variableName, keyClazz, valueClazz) {


  override fun getOptional(): Optional<Map<K, V>> {
    return Optional.ofNullable(
      getOrNull(
        historyService.createHistoricVariableInstanceQuery()
          .executionIdIn(executionId)
          .variableName(variableName)
          .singleResult().value
      )
    )
  }

  override fun getLocal(): Map<K, V> {
    throw UnsupportedOperationException("Can't get a local variable on a historic execution")
  }

  override fun getLocalOptional(): Optional<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on a historic execution")
  }

  override fun getLocalOrDefault(defaultValue: Map<K, V>): Map<K, V> {
    throw UnsupportedOperationException("Can't get a local variable on a historic execution")
  }

  override fun getLocalOrNull(): Map<K, V> {
    throw UnsupportedOperationException("Can't get a local variable on a historic execution")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on a historic execution")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on a historic execution")
  }

  override fun set(value: Map<K, V>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on a historic execution")
  }

  override fun setLocal(value: Map<K, V>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on a historic execution")
  }

}
