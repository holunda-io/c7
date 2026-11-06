package org.camunda.community.process_test_coverage.sonar

import org.assertj.core.api.Assertions.assertThat
import org.camunda.community.process_test_coverage.core.export.CoverageStateJsonExporter
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.sonar.api.batch.sensor.SensorDescriptor
import java.io.File
import java.io.Serializable

class ProcessTestCoverageProjectSensorTest {
  private val sensor = ProcessTestCoverageProjectSensor()

  @Test
  fun testDescribe() {
    val descriptor: SensorDescriptor = mock<SensorDescriptor>()
    sensor.describe(descriptor)
    verify(descriptor).name("Camunda Process Test Coverage (Project)")
  }

  @Test
  fun shouldSkipIfNoReport() {
    val file = createMockedInputFile("target/test-classes/one_result", "/process.bpmn")
    val context = createMockedSensorContext(
      arrayOf("target/test-classes/one_result/unknown.json"),
      listOf(file)
    )
    sensor.execute(context)
    verify(context, times(0)).newMeasure<Serializable>()
  }

  @Test
  fun shouldAnalyseOneResult() {
    val file = createMockedInputFile("target/test-classes", "/process.bpmn")
    val context = createMockedSensorContext(
      arrayOf("one_result/report.json"),
      listOf(file)
    )
    val mockedMetrics = createMockedMetrics(context)
    sensor.execute(context)
    verify(context, times(2)).newMeasure<Serializable>()
    assertThat(mockedMetrics.first.singleValue).isEqualTo(100.0)
    assertCoverageStatusReport(
      mockedMetrics.second.singleValue,
      File("target/test-classes/one_result/expected_result.json")
    )
  }

  @Test
  fun shouldAnalyseTwoResults() {
    val file = createMockedInputFile("target/test-classes", "/process.bpmn")
    val context = createMockedSensorContext(
      arrayOf("two_results/**/report.json"),
      listOf(file)
    )
    val mockedMetrics = createMockedMetrics(context)
    sensor.execute(context)
    verify(context, times(2)).newMeasure<Serializable>()
    assertThat(mockedMetrics.first.singleValue).isEqualTo(100.0)
    assertCoverageStatusReport(
      mockedMetrics.second.singleValue,
      File("target/test-classes/two_results/expected_result.json")
    )
  }

  @Test
  fun shouldAnalyseMultipleModules() {
    val file = createMockedInputFile("target/test-classes", "/process.bpmn")
    val file2 = createMockedInputFile("target/test-classes", "/process2.bpmn")
    val context = createMockedSensorContext(
      arrayOf("multiple_projects/**/report.json"),
      listOf(file, file2)
    )
    val mockedMetrics = createMockedMetrics(context)
    sensor.execute(context)
    verify(context, times(2)).newMeasure<Serializable>()
    assertThat(mockedMetrics.first.singleValue).isEqualTo((5.0 / 6.0) * 100.0)
    assertCoverageStatusReport(
      mockedMetrics.second.singleValue,
      File("target/test-classes/multiple_projects/expected_result.json")
    )
  }

  private fun assertCoverageStatusReport(value: String, expectedResult: File) {
    val coverageResult = CoverageStateJsonExporter.readCoverageStateResult(value)
    val expectedCoverageResult = CoverageStateJsonExporter.readCoverageStateResult(
      expectedResult.readText()
    )
    assertThat(coverageResult.models).containsExactlyInAnyOrderElementsOf(expectedCoverageResult.models)
    assertThat(coverageResult.suites).containsExactlyInAnyOrderElementsOf(expectedCoverageResult.suites)
  }


}
