package io.holunda.camunda.bpm.data.itest

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import com.tngtech.jgiven.integration.spring.JGivenStage
import io.holunda.camunda.bpm.data.factory.VariableFactory
import org.assertj.core.api.Assertions
import org.camunda.bpm.engine.HistoryService
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

/**
 * Base assert stage.
 */
@JGivenStage
class AssertStage : Stage<AssertStage>() {

  @Autowired
  @ProvidedScenarioState
  lateinit var historyService: HistoryService

  fun variables_had_value(readValues: Map<String, Any>, variablesWithValue: Set<Pair<VariableFactory<*>, Any>>): AssertStage {
    variablesWithValue.forEach {
      Assertions.assertThat(readValues).containsEntry(it.first.name, it.second)
    }
    return self()
  }

  fun variables_had_not_value(readValues: Map<String, Any>, vararg variableWithValue: VariableFactory<*>): AssertStage {
    val emptyOptional = Optional.empty<Any>()

    variableWithValue.forEach {
      Assertions.assertThat(readValues).containsEntry(it.name, emptyOptional)
    }
    return self()
  }

  fun variables_had_historic_value(processInstanceId: String,  variablesWithValue: Set<Pair<VariableFactory<*>, Any>>): AssertStage {
    variablesWithValue.forEach {
       Assertions.assertThat(it.first.from(historyService, processInstanceId).get()).isEqualTo(it.second)
    }
    return self()
  }
}
