package io.holunda.camunda.platform.adminprocess._test

import com.github.javafaker.Faker
import io.holunda.camunda.bpm.data.CamundaBpmData
import io.holunda.camunda.platform.adminprocess.AdminProcess
import io.holunda.camunda.platform.adminprocess.AdminProcessRegistry.Companion.logger
import io.holunda.camunda.platform.adminprocess.CamundaAdminProcessRegistryLib.adminProcess
import io.holunda.camunda.platform.adminprocess.form.BooleanField
import io.holunda.camunda.platform.adminprocess.form.DateField
import io.holunda.camunda.platform.adminprocess.form.LongField
import io.holunda.camunda.platform.adminprocess.form.StringField
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

fun main(args: Array<String>) = runApplication<CamundaAdminProcessRegistryTestApplication>(*args).let { }

data class SomeData(
  val id: String,
  val name: String,
  val age: Int
)

enum class SomeEnum(val id:String) {
  FOO("foo"),
  BAR("bar"),
  ;
}

@SpringBootApplication
@EnableProcessApplication
class CamundaAdminProcessRegistryTestApplication {

  @Bean
  fun helloWorldAdminProcess(): AdminProcess {
    val stringField = StringField("fooId", "Foo - enter your name")
    val dateField = DateField("dateId", "Date - select some magic")
    val numberField = LongField("longId", "A number")
    val booleanField = BooleanField("booleanId", "Yes or no?")

    return adminProcess(
      activityId = "helloWorld",
      label = "Hello World 2",
      formFields = listOf(stringField, dateField, numberField, booleanField)
    ) {
      val variables = CamundaBpmData.reader(it)

      logger.info { """ Hi, I am the process running with:
          * foo: ${variables.get(stringField)}
          * date: ${variables.get(dateField)}
          * number: ${variables.get(numberField)}
          * yes?: ${variables.get(booleanField)}
        """.trimIndent()
      }
    }
  }

  @Bean
  fun fake() = JFakerProvider()

  class JFakerProvider {
    private val faker = Faker(Locale.GERMAN)

    fun randomName(): String = faker.name().fullName()

    fun randomDate() : Date = Date.from(Instant.now().plus(10, ChronoUnit.DAYS))
  }
}
