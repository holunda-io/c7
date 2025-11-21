package io.holunda.camunda.bpm.data.reader

import io.holunda.camunda.bpm.data.factory.VariableFactory
import org.camunda.bpm.engine.HistoryService
import java.util.Objects
import java.util.Optional

class HistoryServiceVariableReader(private val historyService: HistoryService, private val executionId: String) : VariableReader {
  override fun <T> getOptional(variableFactory: VariableFactory<T>): Optional<T> {
    return variableFactory.from(historyService, executionId).getOptional()
  }

  override fun <T> get(variableFactory: VariableFactory<T>): T {
    return variableFactory.from(historyService, executionId).get()
  }

  override fun <T> getLocal(variableFactory: VariableFactory<T>): T {
    return variableFactory.from(historyService, executionId).getLocal()
  }

  override fun <T> getLocalOptional(variableFactory: VariableFactory<T>): Optional<T> {
    return variableFactory.from(historyService, executionId).getLocalOptional()
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as HistoryServiceVariableReader
    return if (historyService != that.historyService) false else executionId == that.executionId
  }

  override fun hashCode(): Int {
    return Objects.hash(historyService, executionId)
  }
}