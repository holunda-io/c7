package org.camunda.community.rest.itest

import com.tngtech.jgiven.annotation.As
import io.toolisticon.testing.jgiven.AND
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.THEN
import org.camunda.bpm.engine.RepositoryService
import org.camunda.community.rest.itest.stages.*
import org.junit.jupiter.api.Test
import org.springframework.test.annotation.DirtiesContext

@As("Creates bpmn process model instance query")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class RepositoryServiceModelInstanceQueryITest :
  CamundaRestClientITestBase<RepositoryService, RepositoryServiceActionStage, RepositoryServiceAssertStage>() {

  @Test
  fun `find bpmn model instance by process definition id`() {
    val processDefinitionKey = processDefinitionKey()

    GIVEN
      .no_deployment_exists()
      .AND
      .process_is_deployed(processDefinitionKey)
    THEN
      .bpmn_model_query_succeeds(GIVEN.processDefinition.id)
  }
}
