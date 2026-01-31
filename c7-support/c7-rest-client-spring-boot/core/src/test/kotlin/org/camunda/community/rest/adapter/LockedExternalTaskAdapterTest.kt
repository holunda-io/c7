package org.camunda.community.rest.adapter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.camunda.bpm.engine.variable.Variables
import org.camunda.community.rest.client.model.LockedExternalTaskDto
import org.camunda.community.rest.client.model.VariableValueDto
import org.camunda.community.rest.impl.toRequiredDate
import org.camunda.community.rest.variables.ValueMapper
import org.camunda.community.rest.variables.ValueTypeRegistration
import org.camunda.community.rest.variables.ValueTypeResolverImpl
import org.camunda.community.rest.variables.serialization.JsonValueSerializer
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class LockedExternalTaskAdapterTest {

  private val objectMapper = jacksonObjectMapper()
  private val typeResolver = ValueTypeResolverImpl()
  private val typeRegistration = ValueTypeRegistration()
  private val valueMapper = ValueMapper(
    objectMapper = objectMapper,
    valueTypeResolver = typeResolver,
    valueTypeRegistration = typeRegistration,
    valueSerializers = listOf(JsonValueSerializer(objectMapper)),
    serializationFormat = Variables.SerializationDataFormats.JSON,
    customValueSerializers = listOf()
  )

  private val dto = LockedExternalTaskDto()
    .id("id")
    .processInstanceId("processInstanceId")
    .tenantId("tenantId")
    .activityId("activityId")
    .activityInstanceId("activityInstanceId")
    .businessKey("businessKey")
    .errorDetails("errorDetails")
    .errorMessage("errorMessage")
    .executionId("executionId")
    .extensionProperties(mapOf("prop1" to "value1"))
    .lockExpirationTime(OffsetDateTime.now())
    .priority(1L)
    .processDefinitionId("processDefinitionId")
    .processDefinitionKey("processDefinitionKey")
    .processDefinitionVersionTag("processDefinitionVersionTag")
    .retries(1)
    .suspended(true)
    .topicName("topicName")
    .variables(mapOf("var1" to VariableValueDto().type("integer").value(1)))
    .workerId("workerId")
    .createTime(OffsetDateTime.now())

  @Test
  fun `should delegate`() {
    val lockedExternalTaskBean = LockedExternalTaskBean.fromDto(dto = dto, valueMapper = valueMapper)
    val lockedExternalTaskAdapter = LockedExternalTaskAdapter(lockedExternalTaskBean)
    assertThat(lockedExternalTaskAdapter).usingRecursiveComparison().ignoringFields("lockedExternalTaskBean")
      .isEqualTo(lockedExternalTaskBean)
  }

  @Test
  fun `should construct from dto`() {
    val bean = LockedExternalTaskBean.fromDto(dto = dto, valueMapper = valueMapper)
    assertThat(bean).usingRecursiveComparison().ignoringFields("lockExpirationTime", "variables", "createTime").isEqualTo(dto)
    assertThat(bean.lockExpirationTime).isEqualTo(dto.lockExpirationTime.toInstant())
    assertThat(bean.variables).containsEntry("var1", 1)
    assertThat(bean.createTime).isEqualTo(dto.createTime.toRequiredDate())
  }
}
