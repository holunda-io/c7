package io.holunda.camunda.testing.rest

import org.camunda.bpm.engine.impl.util.ClockUtil
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.DispatcherServlet

/** Registers test endpoints only when explicitly enabled in a servlet application. */
@AutoConfiguration
@ConditionalOnClass(ClockUtil::class, DispatcherServlet::class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = "camunda.bpm.test-rest", name = ["enabled"], havingValue = "true")
class CamundaTestRestAutoConfiguration {

  @Bean
  fun camundaClockController() = CamundaClockController()
}
