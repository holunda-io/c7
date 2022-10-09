
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
* _NativeExecutionQuery_
* _NativeProcessInstanceQuery_
* ProcessElementInstance
* **ProcessInstance**
* _ProcessInstanceModificationBuilder_
* _ProcessInstanceModificationInstantiationBuilder_
* _ProcessInstanceQuery_
* **ProcessInstanceWithVariables**
* _ProcessInstantiationBuilder_
* _RestartProcessInstanceBuilder_
* _SignalEventReceivedBuilder_
* TransitionInstance
* UpdateProcessInstancesRequest
* UpdateProcessInstancesSuspensionStateBuilder
* UpdateProcessInstanceSuspensionStateBuilder
* UpdateProcessInstanceSuspensionStateSelectBuilder
* UpdateProcessInstanceSuspensionStateTenantBuilder
* **VariableInstance**
* _VariableInstanceQuery_
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

* **ExternalTask**
* _ExternalTaskQuery_
* _ExternalTaskQueryBuilder_
* _ExternalTaskQueryTopicBuilder_
* **LockedExternalTask**
* _UpdateExternalTaskRetriesBuilder_
* _UpdateExternalTaskRetriesSelectBuilder_

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

* **Group**
* _GroupQuery_
* NativeUserQuery (?)
* PasswordPolicy (?)
* PasswordPolicyResult (?)
* PasswordPolicyRule (?)
* Picture (?)
* **Tenant**
* _TenantQuery_
* **User**
* _UserQuery_

## package org.camunda.bpm.engine.management;

* ActivityStatistics
* ActivityStatisticsQuery
* DeploymentStatistics
* DeploymentStatisticsQuery
* IncidentStatistics
* **JobDefinition**
* _JobDefinitionQuery_
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
* **CaseDefinition**
* _CaseDefinitionQuery_
* **DecisionDefinition**
* _DecisionDefinitionQuery_
* **DecisionRequirementsDefinition**
* _DecisionRequirementsDefinitionQuery_
* _DeleteProcessDefinitionsBuilder_
* _DeleteProcessDefinitionsSelectBuilder_
* _DeleteProcessDefinitionsTenantBuilder_
* **Deployment**
* _DeploymentBuilder_
* _DeploymentHandler_
* _DeploymentHandlerFactory_
* _DeploymentQuery_
* **DeploymentWithDefinitions**
* DiagramEdge
* DiagramEdgeWaypoint
* DiagramElement
* DiagramLayout
* DiagramNode
* ProcessApplicationDeployment
* ProcessApplicationDeploymentBuilder
* **ProcessDefinition**
* _ProcessDefinitionQuery_
* **Resource**
* **ResourceDefinition**
* ~~ResourceType~~
* ~~ResourceTypes~~
* ResumePreviousBy
* _UpdateProcessDefinitionSuspensionStateBuilder_
* _UpdateProcessDefinitionSuspensionStateSelectBuilder_
* _UpdateProcessDefinitionSuspensionStateTenantBuilder_

## package org.camunda.bpm.engine.task;

* **Attachment**
* **Comment**
* ~~Event~~
* **IdentityLink**
* **IdentityLinkType**
* _NativeTaskQuery_
* **Task**
* TaskCountByCandidateGroupResult (?)
* _TaskQuery_
* TaskReport (?)
