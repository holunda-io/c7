package io.holunda.camunda.bpm.data.itest

import org.camunda.bpm.engine.variable.Variables
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.Optional

class HistoryServiceAdapterITest : CamundaBpmDataITestBase() {

  @Autowired
  lateinit var delegateConfiguration: DelegateConfiguration

  @Test
  fun `should write to task service adapter`() {

    given()
      .process_with_user_task_and_delegate_is_deployed(delegateExpression = "\${${DelegateConfiguration::readFromVariableScope.name}}")
      .and()
      .process_is_started_with_variables(variables = Variables.createVariables())
      .and()
      .process_waits_in_task()

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
      .variables_had_value(readValues = delegateConfiguration.vars, variablesWithValue = createKeyValuePairs())
  }

  @Test
  fun `should remove on task service adapter`() {

    given()
      .process_with_user_task_and_delegate_is_deployed(delegateExpression = "\${${DelegateConfiguration::readOptionalFromVariableScope.name}}")
      .and()
      .process_is_started_with_variables(variables = createVariableMapUntyped())
      .and()
      .process_waits_in_task()

    whenever()
      .task_is_accessed_in_user_task { taskService, taskId ->
        STRING_VAR.on(taskService, taskId).remove()
        LIST_STRING_VAR.on(taskService, taskId).remove()
        SET_STRING_VAR.on(taskService, taskId).remove()
        MAP_STRING_LONG_VAR.on(taskService, taskId).remove()
      }
      .and()
      .task_is_completed()

    then()
      .variables_had_not_value(delegateConfiguration.optionalVars,
        STRING_VAR,
        LIST_STRING_VAR,
        SET_STRING_VAR,
        MAP_STRING_LONG_VAR
      )
      .and()
      .variables_had_value(delegateConfiguration.optionalVars,
        setOf(LONG_VAR to Optional.of(Companion.Values.LONG.value))
      )
  }


  @Test
  fun `should write to variables-map and read task service adapter`() {

    val vars = HashMap<String, Any>()

    given()
      .process_with_user_task_is_deployed()
      .and()
      .process_is_started_with_variables(variables = createVariableMapUntyped())
      .and()
      .process_waits_in_task()

    whenever()
      .task_is_accessed_in_user_task { taskService, taskId ->
        vars[STRING_VAR.name] = STRING_VAR.from(taskService, taskId).get()
        vars[DATE_VAR.name] = DATE_VAR.from(taskService, taskId).get()
        vars[SHORT_VAR.name] = SHORT_VAR.from(taskService, taskId).get()
        vars[INT_VAR.name] = INT_VAR.from(taskService, taskId).get()
        vars[LONG_VAR.name] = LONG_VAR.from(taskService, taskId).get()
        vars[DOUBLE_VAR.name] = DOUBLE_VAR.from(taskService, taskId).get()
        vars[BOOLEAN_VAR.name] = BOOLEAN_VAR.from(taskService, taskId).get()
        vars[COMPLEX_VAR.name] = COMPLEX_VAR.from(taskService, taskId).get()
        vars[LIST_STRING_VAR.name] = LIST_STRING_VAR.from(taskService, taskId).get()
        vars[SET_STRING_VAR.name] = SET_STRING_VAR.from(taskService, taskId).get()
        vars[MAP_STRING_LONG_VAR.name] = MAP_STRING_LONG_VAR.from(taskService, taskId).get()
        vars[COMPLEX_SET_VAR.name] = COMPLEX_SET_VAR.from(taskService, taskId).get()
        vars[COMPLEX_LIST_VAR.name] = COMPLEX_LIST_VAR.from(taskService, taskId).get()
        vars[COMPLEX_MAP_VAR.name] = COMPLEX_MAP_VAR.from(taskService, taskId).get()
        vars[LIST_MAP_STRING_OBJECT_VAR.name] = LIST_MAP_STRING_OBJECT_VAR.from(taskService, taskId).get()
      }

    then()
      .variables_had_value(readValues = vars, variablesWithValue = createKeyValuePairs())
  }

  @Test
  fun `should write local variables to task service adapter`() {

    given()
      .process_with_user_task_and_listener_is_deployed(delegateExpression = "\${${DelegateConfiguration::readLocalFromDelegateTask.name}}")
      .and()
      .process_is_started_with_variables(variables = Variables.createVariables())
      .and()
      .process_waits_in_task()

    whenever()
      .task_is_accessed_in_user_task { taskService, taskId ->
        STRING_VAR.on(taskService, taskId).setLocal(Companion.Values.STRING_LOCAL.value)
        DATE_VAR.on(taskService, taskId).setLocal(Companion.Values.DATE_LOCAL.value)
        SHORT_VAR.on(taskService, taskId).setLocal(Companion.Values.SHORT_LOCAL.value)
        INT_VAR.on(taskService, taskId).setLocal(Companion.Values.INT_LOCAL.value)
        LONG_VAR.on(taskService, taskId).setLocal(Companion.Values.LONG_LOCAL.value)
        DOUBLE_VAR.on(taskService, taskId).setLocal(Companion.Values.DOUBLE_LOCAL.value)
        BOOLEAN_VAR.on(taskService, taskId).setLocal(Companion.Values.BOOLEAN_LOCAL.value)
        COMPLEX_VAR.on(taskService, taskId).setLocal(Companion.Values.COMPLEX_LOCAL.value)
        LIST_STRING_VAR.on(taskService, taskId).setLocal(Companion.Values.LIST_STRING_LOCAL.value)
        SET_STRING_VAR.on(taskService, taskId).setLocal(Companion.Values.SET_STRING_LOCAL.value)
        MAP_STRING_LONG_VAR.on(taskService, taskId).setLocal(Companion.Values.MAP_STRING_DATE_LOCAL.value)
        LIST_MAP_STRING_OBJECT_VAR.on(taskService, taskId).setLocal(Companion.Values.LIST_MAP_STRING_OBJECT_LOCAL.value)
        COMPLEX_SET_VAR.on(taskService, taskId).setLocal(Companion.Values.COMPLEX_SET_LOCAL.value)
        COMPLEX_LIST_VAR.on(taskService, taskId).setLocal(Companion.Values.COMPLEX_LIST_LOCAL.value)
        COMPLEX_MAP_VAR.on(taskService, taskId).setLocal(Companion.Values.COMPLEX_MAP_LOCAL.value)
      }
      .and()
      .task_is_completed()

    then()
      .variables_had_value(readValues = delegateConfiguration.vars, variablesWithValue = createKeyLocalValuePairs())
  }

}