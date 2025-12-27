package org.camunda.community.rest.config

import org.camunda.community.rest.impl.RemoteDecisionService
import org.camunda.community.rest.impl.RemoteExternalTaskService
import org.camunda.community.rest.impl.RemoteHistoryService
import org.camunda.community.rest.impl.RemoteRepositoryService
import org.camunda.community.rest.impl.RemoteRuntimeService
import org.camunda.community.rest.impl.RemoteTaskService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@AutoConfiguration
@Import(
  RemoteExternalTaskService::class,
  RemoteHistoryService::class,
  RemoteRepositoryService::class,
  RemoteRuntimeService::class,
  RemoteTaskService::class,
  RemoteDecisionService::class
)
class CamundaRemoteServicesConfiguration
