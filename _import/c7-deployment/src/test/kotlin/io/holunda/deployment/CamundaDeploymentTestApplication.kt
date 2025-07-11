package io.holunda.deployment

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableMBeanExport
import org.springframework.jmx.support.RegistrationPolicy

fun main(args: Array<String>) {
  runApplication<CamundaDeploymentTestApplication>(*args)
}

@SpringBootApplication
@EnableProcessApplication
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
class CamundaDeploymentTestApplication
