package org.camunda.community.rest.adapter

import org.camunda.bpm.application.ProcessApplicationReference
import org.camunda.bpm.application.ProcessApplicationRegistration
import org.camunda.bpm.engine.ManagementService
import org.camunda.bpm.engine.batch.Batch
import org.camunda.bpm.engine.batch.BatchQuery
import org.camunda.bpm.engine.batch.BatchStatisticsQuery
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery
import org.camunda.bpm.engine.management.ActivityStatisticsQuery
import org.camunda.bpm.engine.management.DeploymentStatisticsQuery
import org.camunda.bpm.engine.management.JobDefinitionQuery
import org.camunda.bpm.engine.management.MetricsQuery
import org.camunda.bpm.engine.management.ProcessDefinitionStatisticsQuery
import org.camunda.bpm.engine.management.SchemaLogQuery
import org.camunda.bpm.engine.management.SetJobRetriesBuilder
import org.camunda.bpm.engine.management.SetJobRetriesByJobsAsyncBuilder
import org.camunda.bpm.engine.management.SetJobRetriesByProcessAsyncBuilder
import org.camunda.bpm.engine.management.TableMetaData
import org.camunda.bpm.engine.management.TablePageQuery
import org.camunda.bpm.engine.management.UpdateJobDefinitionSuspensionStateSelectBuilder
import org.camunda.bpm.engine.management.UpdateJobSuspensionStateSelectBuilder
import org.camunda.bpm.engine.runtime.JobQuery
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery
import org.camunda.bpm.engine.telemetry.TelemetryData
import org.camunda.community.rest.impl.RemoteManagementService
import org.camunda.community.rest.impl.implementedBy
import java.sql.Connection
import java.util.Date

/**
 * Adapter for implementing management service.
 */
abstract class AbstractManagementServiceAdapter : ManagementService {

  override fun registerProcessApplication(
    deploymentId: String?,
    reference: ProcessApplicationReference?
  ): ProcessApplicationRegistration? {
    TODO("Not yet implemented")
  }

  override fun unregisterProcessApplication(deploymentId: String?, removeProcessDefinitionsFromCache: Boolean) {
    TODO("Not yet implemented")
  }

  override fun unregisterProcessApplication(deploymentIds: Set<String?>?, removeProcessDefinitionsFromCache: Boolean) {
    TODO("Not yet implemented")
  }

  override fun getProcessApplicationForDeployment(deploymentId: String?): String? {
    TODO("Not yet implemented")
  }

  override fun getTableCount(): Map<String?, Long?>? {
    TODO("Not yet implemented")
  }

  override fun getTableName(entityClass: Class<*>?): String? {
    TODO("Not yet implemented")
  }

  override fun getTableMetaData(tableName: String?): TableMetaData? {
    TODO("Not yet implemented")
  }

  override fun createTablePageQuery(): TablePageQuery? {
    TODO("Not yet implemented")
  }

  override fun createJobQuery(): JobQuery? {
    TODO("Not yet implemented")
  }

  override fun createJobDefinitionQuery(): JobDefinitionQuery? {
    TODO("Not yet implemented")
  }

  override fun executeJob(jobId: String?) {
    TODO("Not yet implemented")
  }

  override fun deleteJob(jobId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionById(jobDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionById(jobDefinitionId: String?, activateJobs: Boolean) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionById(jobDefinitionId: String?, activateJobs: Boolean, activationDate: Date?) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionByProcessDefinitionId(processDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionByProcessDefinitionId(processDefinitionId: String?, activateJobs: Boolean) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionByProcessDefinitionId(processDefinitionId: String?, activateJobs: Boolean, activationDate: Date?) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionByProcessDefinitionKey(processDefinitionKey: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionByProcessDefinitionKey(processDefinitionKey: String?, activateJobs: Boolean) {
    TODO("Not yet implemented")
  }

  override fun activateJobDefinitionByProcessDefinitionKey(
    processDefinitionKey: String?,
    activateJobs: Boolean,
    activationDate: Date?
  ) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionById(jobDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionById(jobDefinitionId: String?, suspendJobs: Boolean) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionById(jobDefinitionId: String?, suspendJobs: Boolean, suspensionDate: Date?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionByProcessDefinitionId(processDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionByProcessDefinitionId(processDefinitionId: String?, suspendJobs: Boolean) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionByProcessDefinitionId(processDefinitionId: String?, suspendJobs: Boolean, suspensionDate: Date?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionByProcessDefinitionKey(processDefinitionKey: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionByProcessDefinitionKey(processDefinitionKey: String?, suspendJobs: Boolean) {
    TODO("Not yet implemented")
  }

  override fun suspendJobDefinitionByProcessDefinitionKey(processDefinitionKey: String?, suspendJobs: Boolean, suspensionDate: Date?) {
    TODO("Not yet implemented")
  }

  override fun activateJobById(jobId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobByJobDefinitionId(jobDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobByProcessInstanceId(processInstanceId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobByProcessDefinitionId(processDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateJobByProcessDefinitionKey(processDefinitionKey: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobById(jobId: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobByJobDefinitionId(jobDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobByProcessInstanceId(processInstanceId: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobByProcessDefinitionId(processDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun suspendJobByProcessDefinitionKey(processDefinitionKey: String?) {
    TODO("Not yet implemented")
  }

  override fun updateJobSuspensionState(): UpdateJobSuspensionStateSelectBuilder? {
    TODO("Not yet implemented")
  }

  override fun updateJobDefinitionSuspensionState(): UpdateJobDefinitionSuspensionStateSelectBuilder? {
    TODO("Not yet implemented")
  }

  override fun setJobRetries(jobId: String?, retries: Int) {
    TODO("Not yet implemented")
  }

  override fun setJobRetries(jobIds: List<String?>?, retries: Int) {
    TODO("Not yet implemented")
  }

  override fun setJobRetries(retries: Int): SetJobRetriesBuilder? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesAsync(jobIds: List<String?>?, retries: Int): Batch? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesAsync(jobQuery: JobQuery?, retries: Int): Batch? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesAsync(
    jobIds: List<String?>?,
    jobQuery: JobQuery?,
    retries: Int
  ): Batch? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesAsync(
    processInstanceIds: List<String?>?,
    query: ProcessInstanceQuery?,
    retries: Int
  ): Batch? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesAsync(
    processInstanceIds: List<String?>?,
    processInstanceQuery: ProcessInstanceQuery?,
    historicProcessInstanceQuery: HistoricProcessInstanceQuery?,
    retries: Int
  ): Batch? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesByJobsAsync(retries: Int): SetJobRetriesByJobsAsyncBuilder? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesByProcessAsync(retries: Int): SetJobRetriesByProcessAsyncBuilder? {
    TODO("Not yet implemented")
  }

  override fun setJobRetriesByJobDefinitionId(jobDefinitionId: String?, retries: Int) {
    TODO("Not yet implemented")
  }

  override fun setJobDuedate(jobId: String?, newDuedate: Date?) {
    TODO("Not yet implemented")
  }

  override fun setJobDuedate(jobId: String?, newDuedate: Date?, cascade: Boolean) {
    TODO("Not yet implemented")
  }

  override fun recalculateJobDuedate(jobId: String?, creationDateBased: Boolean) {
    TODO("Not yet implemented")
  }

  override fun setJobPriority(jobId: String?, priority: Long) {
    TODO("Not yet implemented")
  }

  override fun setOverridingJobPriorityForJobDefinition(jobDefinitionId: String?, priority: Long) {
    TODO("Not yet implemented")
  }

  override fun setOverridingJobPriorityForJobDefinition(jobDefinitionId: String?, priority: Long, cascade: Boolean) {
    TODO("Not yet implemented")
  }

  override fun clearOverridingJobPriorityForJobDefinition(jobDefinitionId: String?) {
    TODO("Not yet implemented")
  }

  override fun getJobExceptionStacktrace(jobId: String?): String? {
    TODO("Not yet implemented")
  }

  override fun getProperties(): Map<String?, String?>? {
    TODO("Not yet implemented")
  }

  override fun setProperty(name: String?, value: String?) {
    TODO("Not yet implemented")
  }

  override fun deleteProperty(name: String?) {
    TODO("Not yet implemented")
  }

  override fun setLicenseKey(licenseKey: String?) {
    TODO("Not yet implemented")
  }

  override fun getLicenseKey(): String? {
    TODO("Not yet implemented")
  }

  override fun deleteLicenseKey() {
    TODO("Not yet implemented")
  }

  override fun databaseSchemaUpgrade(connection: Connection?, catalog: String?, schema: String?): String? {
    TODO("Not yet implemented")
  }

  override fun createProcessDefinitionStatisticsQuery(): ProcessDefinitionStatisticsQuery? {
    TODO("Not yet implemented")
  }

  override fun createDeploymentStatisticsQuery(): DeploymentStatisticsQuery? {
    TODO("Not yet implemented")
  }

  override fun createActivityStatisticsQuery(processDefinitionId: String?): ActivityStatisticsQuery? {
    TODO("Not yet implemented")
  }

  override fun getRegisteredDeployments(): Set<String?>? {
    TODO("Not yet implemented")
  }

  override fun registerDeploymentForJobExecutor(deploymentId: String?) {
    TODO("Not yet implemented")
  }

  override fun unregisterDeploymentForJobExecutor(deploymentId: String?) {
    TODO("Not yet implemented")
  }

  override fun getHistoryLevel(): Int {
    TODO("Not yet implemented")
  }

  override fun createMetricsQuery(): MetricsQuery? {
    TODO("Not yet implemented")
  }

  override fun deleteMetrics(timestamp: Date?) {
    TODO("Not yet implemented")
  }

  override fun deleteMetrics(timestamp: Date?, reporter: String?) {
    TODO("Not yet implemented")
  }

  override fun reportDbMetricsNow() {
    TODO("Not yet implemented")
  }

  override fun getUniqueTaskWorkerCount(startTime: Date?, endTime: Date?): Long {
    TODO("Not yet implemented")
  }

  override fun deleteTaskMetrics(timestamp: Date?) {
    TODO("Not yet implemented")
  }

  override fun createBatchQuery(): BatchQuery {
    implementedBy(RemoteManagementService::class)
  }

  override fun suspendBatchById(batchId: String?) {
    TODO("Not yet implemented")
  }

  override fun activateBatchById(batchId: String?) {
    TODO("Not yet implemented")
  }

  override fun deleteBatch(batchId: String?, cascade: Boolean) {
    TODO("Not yet implemented")
  }

  override fun createBatchStatisticsQuery(): BatchStatisticsQuery? {
    TODO("Not yet implemented")
  }

  override fun createSchemaLogQuery(): SchemaLogQuery? {
    TODO("Not yet implemented")
  }

  override fun toggleTelemetry(enabled: Boolean) {
    TODO("Not yet implemented")
  }

  override fun isTelemetryEnabled(): Boolean? {
    TODO("Not yet implemented")
  }

  override fun getTelemetryData(): TelemetryData? {
    TODO("Not yet implemented")
  }
}
