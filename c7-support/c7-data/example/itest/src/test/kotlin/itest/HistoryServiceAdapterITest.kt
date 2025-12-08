package io.holunda.camunda.bpm.data.itest

import org.camunda.bpm.engine.variable.Variables
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class HistoryServiceAdapterITest : CamundaBpmDataITestBase() {

  @Test
  fun `should read historic variables`() {

    val processInstance = given()
      .process_with_user_task_and_delegate_is_deployed(delegateExpression = "\${${DelegateConfiguration::readFromVariableScope.name}}")
      .and()
      .process_is_started_with_variables(variables = Variables.createVariables())
      .and()
      .process_waits_in_task()
      .processInstance

    whenever()
      .task_is_accessed_in_user_task { taskService, taskId ->
        STRING_VAR.on(taskService, taskId).set(Companion.Values.STRING.value)
        DATE_VAR.on(taskService, taskId).set(Companion.Values.DATE.value)
        SHORT_VAR.on(taskService, taskId).set(Companion.Values.SHORT.value)
        INT_VAR.on(taskService, taskId).set(Companion.Values.INT.value)
        LONG_VAR.on(taskService, taskId).set(Companion.Values.LONG.value)
        DOUBLE_VAR.on(taskService, taskId).set(Companion.Values.DOUBLE.value)
        BOOLEAN_VAR.on(taskService, taskId).set(Companion.Values.BOOLEAN.value)
        COMPLEX_VAR.on(taskService, taskId).set(Companion.Values.COMPLEX.value)
        LIST_STRING_VAR.on(taskService, taskId).set(Companion.Values.LIST_STRING.value)
        SET_STRING_VAR.on(taskService, taskId).set(Companion.Values.SET_STRING.value)
        MAP_STRING_LONG_VAR.on(taskService, taskId).set(Companion.Values.MAP_STRING_LONG.value)
        COMPLEX_SET_VAR.on(taskService, taskId).set(Companion.Values.COMPLEX_SET.value)
        COMPLEX_LIST_VAR.on(taskService, taskId).set(Companion.Values.COMPLEX_LIST.value)
        COMPLEX_MAP_VAR.on(taskService, taskId).set(Companion.Values.COMPLEX_MAP.value)
        LIST_MAP_STRING_OBJECT_VAR.on(taskService, taskId).set(Companion.Values.LIST_MAP_STRING_OBJECT.value)

      }
      .and()
      .task_is_completed()

    then()
      .variables_had_historic_value(processInstance.id, variablesWithValue = createKeyValuePairs())
  }
}