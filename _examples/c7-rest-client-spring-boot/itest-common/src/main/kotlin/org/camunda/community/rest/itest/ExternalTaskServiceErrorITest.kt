package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.WHEN
import org.camunda.bpm.engine.ExternalTaskService
import org.camunda.community.rest.itest.stages.CamundaRestClientITestBase
import org.camunda.community.rest.itest.stages.EXPECT
import org.camunda.community.rest.itest.stages.ExternalTaskServiceActionStage
import org.camunda.community.rest.itest.stages.ExternalTaskServiceAssertStage
import org.junit.jupiter.api.Test
import org.springframework.test.context.TestPropertySource

@As("External Task")
@TestPropertySource(
  properties = [
    "camunda.rest.client.error-decoding.http-codes=404"
  ]
)
class ExternalTaskServiceErrorITest :
  CamundaRestClientITestBase<ExternalTaskService, ExternalTaskServiceActionStage, ExternalTaskServiceAssertStage>() {

  @Test
  fun `should fail completing non-existing external task`() {
    EXPECT.process_engine_exception_is_thrown_caused_by(
      reason = "REST-CLIENT-002 Error during remote Camunda engine invocation with RestException: External task with id not-existing does not exist"
    ) {
      WHEN
        .remoteService.complete("not-existing", "worker-id")
    }
  }
}
