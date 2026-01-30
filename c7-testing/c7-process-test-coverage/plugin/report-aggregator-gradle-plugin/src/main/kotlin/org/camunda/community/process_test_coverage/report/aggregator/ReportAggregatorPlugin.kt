package org.camunda.community.process_test_coverage.report.aggregator

import org.camunda.community.process_test_coverage.core.export.CoverageStateJsonExporter.combineCoverageStateResults
import org.camunda.community.process_test_coverage.core.export.CoverageStateJsonExporter.createCoverageStateResult
import org.camunda.community.process_test_coverage.core.export.CoverageStateJsonExporter.readCoverageStateResult
import org.camunda.community.process_test_coverage.report.CoverageReportUtil
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

abstract class ReportAggregatorPluginExtension {
    var reportDirectory: String = "build/process-test-coverage"
    var outputDirectory: String = "all"
}

class ReportAggregatorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create("aggregateProcessTestCoverage", ReportAggregatorPluginExtension::class.java)
        project.tasks.register("aggregateProcessTestCoverage") {
          it.doLast {
            val outputDirectory = File(File(project.projectDir, extension.reportDirectory), extension.outputDirectory)
            project.allprojects
              .asSequence()
              .map {
                project.logger.debug("Processing module ${it.name}")
                it.fileTree(it.projectDir).apply {
                  this.include("${extension.reportDirectory}/**/report.json")
                  this.exclude("**/${extension.outputDirectory}/report.json")
                }.files
              }
              .flatten()
              .map {
                project.logger.debug("Reading file ${it.path}")
                it.readText(Charsets.UTF_8)
              }
              .reduceOrNull { result1, result2 -> combineCoverageStateResults(result1, result2) }
              ?.let {
                val report = readCoverageStateResult(it)
                CoverageReportUtil.writeReport(
                  createCoverageStateResult(report.suites, report.models), false,
                  outputDirectory, "report.json"
                ) { result -> result }
                CoverageReportUtil.writeReport(
                  createCoverageStateResult(report.suites, report.models), true,
                  outputDirectory, "report.html", CoverageReportUtil::generateHtml)
              } ?: project.logger.warn("No coverage results found, skipping execution")
          }
        }
    }

}
