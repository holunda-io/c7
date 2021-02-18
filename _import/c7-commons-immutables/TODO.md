
## package org.camunda.bpm.engine.authorization;

* Authorization
* AuthorizationQuery
* Groups
* Permission
* Resource

## package org.camunda.bpm.engine.batch;

* **Batch**
* _BatchQuery_
* BatchStatistics
* _BatchStatisticsQuery_

## package org.camunda.bpm.engine.runtime;

* **ActivityInstance**
* _ActivityInstantiationBuilder_
* **CaseExecution**
* _CaseExecutionCommandBuilder_
* _CaseExecutionQuery_
* **CaseInstance**
* _CaseInstanceBuilder_
* _CaseInstanceQuery_
* _ConditionEvaluationBuilder_
* _DeserializationTypeValidator_
* **EventSubscription**
* _EventSubscriptionQuery_
* Execution
* _ExecutionQuery_
* **Incident**
* _IncidentQuery_
* _InstantiationBuilder_
* **Job**
* _JobQuery_
* _MessageCorrelationBuilder_
* MessageCorrelationResult
* MessageCorrelationResultType
* MessageCorrelationResultWithVariables
* ModificationBuilder
* NativeExecutionQuery
* NativeProcessInstanceQuery
* ProcessElementInstance
* ProcessInstance
* ProcessInstanceModificationBuilder
* ProcessInstanceModificationInstantiationBuilder
* ProcessInstanceQuery
* ProcessInstanceWithVariables
* ProcessInstantiationBuilder
* RestartProcessInstanceBuilder
* SignalEventReceivedBuilder
* TransitionInstance
* UpdateProcessInstancesRequest
* UpdateProcessInstancesSuspensionStateBuilder
* UpdateProcessInstanceSuspensionStateBuilder
* UpdateProcessInstanceSuspensionStateSelectBuilder
* UpdateProcessInstanceSuspensionStateTenantBuilder
* VariableInstance
* VariableInstanceQuery
* WhitelistingDeserializationTypeValidator

## package org.camunda.bpm.engine.delegate;

* BaseDelegateExecution
* BpmnError
* BpmnModelExecutionContext
* CaseExecutionListener
* CaseVariableListener
* CmmnModelExecutionContext
* DelegateCaseExecution
* DelegateCaseVariableInstance
* DelegateExecution
* DelegateListener
* DelegateTask
* DelegateVariableInstance
* DelegateVariableMapping
* ExecutionListener
* Expression
* JavaDelegate
* ProcessEngineServicesAware
* TaskListener
* VariableListener
* VariableScope

## package org.camunda.bpm.engine.externaltask;

* ExternalTask
* ExternalTaskQuery
* ExternalTaskQueryBuilder
* ExternalTaskQueryTopicBuilder
* LockedExternalTask
* UpdateExternalTaskRetriesBuilder
* UpdateExternalTaskRetriesSelectBuilder

## package org.camunda.bpm.engine.filter;

* Filter
* FilterQuery

## package org.camunda.bpm.engine.form;

* FormData
* FormField
* FormFieldValidationConstraint
* FormProperty
* FormType
* StartFormData
* TaskFormData

## package org.camunda.bpm.engine.history;

* CleanableHistoricBatchReport
* CleanableHistoricBatchReportResult
* CleanableHistoricCaseInstanceReport
* CleanableHistoricCaseInstanceReportResult
* CleanableHistoricDecisionInstanceReport
* CleanableHistoricDecisionInstanceReportResult
* CleanableHistoricProcessInstanceReport
* CleanableHistoricProcessInstanceReportResult
* DurationReportResult
* ExternalTaskState
* HistoricActivityInstance
* HistoricActivityInstanceQuery
* HistoricActivityStatistics
* HistoricActivityStatisticsQuery
* HistoricCaseActivityInstance
* HistoricCaseActivityInstanceQuery
* HistoricCaseActivityStatistics
* HistoricCaseActivityStatisticsQuery
* HistoricCaseInstance
* HistoricCaseInstanceQuery
* HistoricDecisionInputInstance
* HistoricDecisionInstance
* HistoricDecisionInstanceQuery
* HistoricDecisionInstanceStatistics
* HistoricDecisionInstanceStatisticsQuery
* HistoricDecisionOutputInstance
* HistoricDetail
* HistoricDetailQuery
* HistoricExternalTaskLog
* HistoricExternalTaskLogQuery
* HistoricFormField
* HistoricFormProperty
* HistoricIdentityLinkLog
* HistoricIdentityLinkLogQuery
* HistoricIncident
* HistoricIncidentQuery
* HistoricJobLog
* HistoricJobLogQuery
* HistoricProcessInstance
* HistoricProcessInstanceQuery
* HistoricProcessInstanceReport
* HistoricTaskInstance
* HistoricTaskInstanceQuery
* HistoricTaskInstanceReport
* HistoricTaskInstanceReportResult
* HistoricVariableInstance
* HistoricVariableInstanceQuery
* HistoricVariableUpdate
* IncidentState
* JobState
* NativeHistoricActivityInstanceQuery
* NativeHistoricCaseActivityInstanceQuery
* NativeHistoricCaseInstanceQuery
* NativeHistoricDecisionInstanceQuery
* NativeHistoricProcessInstanceQuery
* NativeHistoricTaskInstanceQuery
* NativeHistoricVariableInstanceQuery
* ReportResult
* SetRemovalTimeSelectModeForHistoricBatchesBuilder
* SetRemovalTimeSelectModeForHistoricDecisionInstancesBuilder
* SetRemovalTimeSelectModeForHistoricProcessInstancesBuilder
* SetRemovalTimeToHistoricBatchesBuilder
* SetRemovalTimeToHistoricDecisionInstancesBuilder
* SetRemovalTimeToHistoricProcessInstancesBuilder
* UserOperationLogEntry
* UserOperationLogQuery

## package org.camunda.bpm.engine.identity;

* Group
* GroupQuery
* NativeUserQuery
* PasswordPolicy
* PasswordPolicyResult
* PasswordPolicyRule
* Picture
* Tenant
* TenantQuery
* User
* UserQuery

## package org.camunda.bpm.engine.management;

* ActivityStatistics
* ActivityStatisticsQuery
* DeploymentStatistics
* DeploymentStatisticsQuery
* IncidentStatistics
* JobDefinition
* JobDefinitionQuery
* MetricIntervalValue
* Metrics
* MetricsQuery
* ProcessDefinitionStatistics
* ProcessDefinitionStatisticsQuery
* SchemaLogEntry
* SchemaLogQuery
* TableMetaData
* TablePage
* TablePageQuery
* UpdateJobDefinitionSuspensionStateBuilder
* UpdateJobDefinitionSuspensionStateSelectBuilder
* UpdateJobDefinitionSuspensionStateTenantBuilder
* UpdateJobSuspensionStateBuilder
* UpdateJobSuspensionStateSelectBuilder
* UpdateJobSuspensionStateTenantBuilder

## package org.camunda.bpm.engine.repository;

* CandidateDeployment
* CaseDefinition
* _CaseDefinitionQuery_
* DecisionDefinition
* _DecisionDefinitionQuery_
* DecisionRequirementsDefinition
* _DecisionRequirementsDefinitionQuery_
* DeleteProcessDefinitionsBuilder
* DeleteProcessDefinitionsSelectBuilder
* DeleteProcessDefinitionsTenantBuilder
* Deployment
* DeploymentBuilder
* DeploymentHandler
* DeploymentHandlerFactory
* _DeploymentQuery_
* DeploymentWithDefinitions
* DiagramEdge
* DiagramEdgeWaypoint
* DiagramElement
* DiagramLayout
* DiagramNode
* ProcessApplicationDeployment
* ProcessApplicationDeploymentBuilder
* ProcessDefinition
* ProcessDefinitionQuery
* Resource
* ResourceDefinition
* ResourceType
* ResourceTypes
* ResumePreviousBy
* UpdateProcessDefinitionSuspensionStateBuilder
* UpdateProcessDefinitionSuspensionStateSelectBuilder
* UpdateProcessDefinitionSuspensionStateTenantBuilder

## package org.camunda.bpm.engine.task;

* **Attachment**
* Comment
* ~~Event~~
* **IdentityLink**
* IdentityLinkType
* _NativeTaskQuery_
* **Task**
* TaskCountByCandidateGroupResult (?)
* _TaskQuery_
* TaskReport (?)
