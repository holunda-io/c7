package io.holunda.camunda.platform.adminprocess._test

import io.holunda.camunda.bpm.data.CamundaBpmData
import io.holunda.camunda.platform.adminprocess.AdminProcess
import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry.Companion.logger
import io.holunda.camunda.platform.adminprocess.CamundaAdminProcessRegistryLib.adminProcess
import io.holunda.camunda.platform.adminprocess.DateField
import io.holunda.camunda.platform.adminprocess.StringField
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

fun main(args: Array<String>) = runApplication<CamundaAdminProcessRegistryTestApplication>(*args).let { }

@SpringBootApplication
@EnableProcessApplication
class CamundaAdminProcessRegistryTestApplication {

  @Bean
  fun helloWorldAdminProcess(): AdminProcess {
    val foo = StringField("foo", "Foo - enter your name")
    val date = DateField("date", "Date - select some magic")

    return adminProcess(
      activityId = "helloWorld",
      label = "Hello World 2",
      formFields = listOf(foo, date)
    ) {
      val variables = CamundaBpmData.reader(it)

      logger.info { """ Hi, I am the process running with:
          * foo: ${variables.get(foo)}
          * date: ${variables.get(date)}
        """.trimIndent()
      }
    }
  }
}
