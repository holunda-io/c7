package io.holunda.deployment

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
  runApplication<CamundaDeploymentTestApplication>(*args)
}

@SpringBootApplication
@EnableProcessApplication
class CamundaDeploymentTestApplication
