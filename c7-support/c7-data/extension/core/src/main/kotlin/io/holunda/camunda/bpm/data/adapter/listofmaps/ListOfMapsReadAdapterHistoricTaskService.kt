package io.holunda.camunda.bpm.data.adapter.listofmaps

import org.camunda.bpm.engine.HistoryService
import java.util.*
import kotlin.collections.Map

/**
 * Read adapter for history service.
 *
 * @param [K] key type.
 * @param [V] value type.
 */
class ListOfMapsReadAdapterHistoricTaskService<K, V>(
  private val historyService: HistoryService,
  private val taskId: String,
  variableName: String,
  keyClazz: Class<K>,
  valueClazz: Class<V>
) : AbstractListOfMapsReadWriteAdapter<K, V>(variableName, keyClazz, valueClazz) {



  override fun getOptional(): Optional<List<Map<K, V>>> {
    return Optional.ofNullable(
      getOrNull(
        historyService.createHistoricVariableInstanceQuery()
          .taskIdIn(taskId)
          .variableName(variableName)
          .singleResult().value
      )
    )
  }

  override fun set(value: List<Map<K, V>>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a variable on an historic task")
  }

  override fun setLocal(value: List<Map<K, V>>, isTransient: Boolean) {
    throw UnsupportedOperationException("Can't set a local variable on an historic task")
  }

  override fun getLocal(): List<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOptional(): Optional<List<Map<K, V>>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOrDefault(defaultValue: List<Map<K, V>>): List<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun getLocalOrNull(): List<Map<K, V>> {
    throw UnsupportedOperationException("Can't get a local variable on an historic task")
  }

  override fun remove() {
    throw UnsupportedOperationException("Can't remove a variable on an historic task")
  }

  override fun removeLocal() {
    throw UnsupportedOperationException("Can't remove a local variable on an historic task")
  }
}
