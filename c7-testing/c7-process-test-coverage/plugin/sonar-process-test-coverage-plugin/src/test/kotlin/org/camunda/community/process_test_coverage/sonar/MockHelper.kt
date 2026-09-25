package org.camunda.community.process_test_coverage.sonar

import org.mockito.ArgumentCaptor
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.sonar.api.batch.fs.FilePredicate
import org.sonar.api.batch.fs.FilePredicates
import org.sonar.api.batch.fs.FileSystem
import org.sonar.api.batch.fs.InputFile
import org.sonar.api.batch.measure.Metric
import org.sonar.api.batch.sensor.SensorContext
import org.sonar.api.batch.sensor.measure.NewMeasure
import org.sonar.api.config.Configuration
import org.sonar.api.scanner.fs.InputProject
import java.io.ByteArrayInputStream
import java.io.File
import java.io.Serializable
import java.nio.file.Paths
import kotlin.streams.asStream


fun createMockedSensorContext(reportPaths: Array<String>, testFiles: List<InputFile>): SensorContext {
  val config = mock<Configuration> {
    whenever { it.getStringArray(ReportsProvider.REPORT_PATHS_PROPERTY_KEY) } doReturn reportPaths
  }
  val predicate = mock<FilePredicate> {}
  val predicates = mock<FilePredicates> {
    whenever { it.hasLanguage(any()) } doReturn predicate
  }
  val fileSystem = mock<FileSystem> {
    whenever { it.baseDir() } doReturn File(".")
    whenever { it.inputFiles(anyOrNull()) } doReturn testFiles
    whenever { it.predicates() } doReturn predicates
  }
  return mock<SensorContext> {
    whenever { it.fileSystem() } doReturn fileSystem
    whenever { it.config() } doReturn config
    whenever { it.project() } doReturn mock<InputProject>()
  }
}

fun createMockedInputFile(relativePath: String, contentFile: String): InputFile {

  val content = BpmnLanguageTest::class.java.getResource(contentFile)?.readText()
    ?: throw IllegalStateException("Could not find $contentFile")
  val path = Paths.get(relativePath, contentFile)

  return mock<InputFile> {
    whenever { it.relativePath() } doReturn path.toString()
    whenever { it.filename() } doReturn path.fileName.toString()
    whenever { it.uri() } doReturn path.toUri()
    whenever { it.type() } doReturn InputFile.Type.MAIN
    whenever { it.language() } doReturn BpmnLanguage.KEY
    whenever { it.contents() } doReturn content
    whenever { it.inputStream() } doReturn ByteArrayInputStream(content.toByteArray())
    whenever { it.lines() } doReturn (content.lineSequence().asStream().count().toInt())
  }

}

fun createMockedMetrics(context: SensorContext): Pair<KArgumentCaptor<Double>, KArgumentCaptor<String>> {
  val argumentCaptorCoverage = argumentCaptor<Double>()
  val argumentCaptorReport = argumentCaptor<String>()
  val coverageMeasure = mock<NewMeasure<Double>> {
    whenever { it.withValue(argumentCaptorCoverage.capture()) } doReturn it
  }
  val reportMeasure = mock<NewMeasure<String>> {
    whenever { it.withValue(argumentCaptorReport.capture()) } doReturn it
  }
  val measure = mock<NewMeasure<Serializable>> {
    on { on(any()) } doReturn it
    on { forMetric(ProcessTestCoverageMetrics.PROCESS_TEST_COVERAGE as Metric<Serializable?>) } doReturn (coverageMeasure as NewMeasure<Serializable>)
    on { forMetric(ProcessTestCoverageMetrics.PROCESS_TEST_COVERAGE_REPORT as Metric<Serializable?>) } doReturn (reportMeasure as NewMeasure<Serializable>)
  }

  whenever(context.newMeasure<Serializable>()).doReturn(measure)
  return Pair(argumentCaptorCoverage, argumentCaptorReport)
}
