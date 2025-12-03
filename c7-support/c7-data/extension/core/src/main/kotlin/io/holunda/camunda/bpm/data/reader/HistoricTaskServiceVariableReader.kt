package io.holunda.camunda.bpm.data.reader

import io.holunda.camunda.bpm.data.factory.VariableFactory
import org.camunda.bpm.engine.HistoryService
import java.util.Objects
import java.util.Optional

class HistoricTaskServiceVariableReader(private val historyService: HistoryService, private val taskId: String) : VariableReader {
  override fun <T> getOptional(variableFactory: VariableFactory<T>): Optional<T> {
    return variableFactory.fromTask(historyService, taskId).getOptional()
  }

  override fun <T> get(variableFactory: VariableFactory<T>): T {
    return variableFactory.fromTask(historyService, taskId).get()
  }

  override fun <T> getLocal(variableFactory: VariableFactory<T>): T {
    return variableFactory.fromTask(historyService, taskId).getLocal()
  }

  override fun <T> getLocalOptional(variableFactory: VariableFactory<T>): Optional<T> {
    return variableFactory.fromTask(historyService, taskId).getLocalOptional()
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as HistoricTaskServiceVariableReader
    return if (historyService != that.historyService) false else taskId == that.taskId
  }

  override fun hashCode(): Int {
    return Objects.hash(historyService, taskId)
  }
}