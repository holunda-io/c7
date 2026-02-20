package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.AND
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.THEN
import io.toolisticon.testing.jgiven.WHEN
import org.camunda.bpm.engine.MismatchingMessageCorrelationException
import org.camunda.bpm.engine.RuntimeService
import org.camunda.community.rest.itest.stages.CamundaRestClientITestBase
import org.camunda.community.rest.itest.stages.RuntimeServiceActionStage
import org.camunda.community.rest.itest.stages.RuntimeServiceAssertStage
import org.junit.jupiter.api.Test
import org.springframework.test.context.TestPropertySource

@As("Unwrapped Exceptions")
@TestPropertySource(
  properties = ["camunda.rest.client.error-decoding.wrap-exceptions=false"]
)
class UnwrappedExceptionsITest :
  CamundaRestClientITestBase<RuntimeService, RuntimeServiceActionStage, RuntimeServiceAssertStage>() {

  @Test
  fun `should fail to correlate message with waiting instance`() {
    val processDefinitionKey = processDefinitionKey()
    val messageName = "myEventMessage"
    val userTaskId = "user-task"
    GIVEN
      .process_with_intermediate_message_catch_event_is_deployed(processDefinitionKey, userTaskId, messageName)
      .AND
      .process_is_started_by_key(processDefinitionKey)
      .AND

    THEN
      .exception_is_thrown(
        clazz = MismatchingMessageCorrelationException::class.java,
        reason = "Cannot correlate message 'wrong-message': No process definition or execution matches the parameters"
      ) {
        WHEN
          .remoteService
          .correlateMessage("wrong-message")
      }
  }

}
