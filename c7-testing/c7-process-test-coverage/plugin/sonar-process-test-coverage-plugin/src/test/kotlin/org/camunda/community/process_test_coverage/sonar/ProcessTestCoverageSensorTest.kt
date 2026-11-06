package org.camunda.community.process_test_coverage.sonar

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.sonar.api.batch.sensor.SensorDescriptor
import java.io.Serializable


class ProcessTestCoverageSensorTest {

  private val sensor = ProcessTestCoverageSensor()

  @Test
  fun testDescribe() {
    val descriptor: SensorDescriptor = mock<SensorDescriptor>()
    sensor.describe(descriptor)
    verify(descriptor).name("Camunda Process Test Coverage")
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
      arrayOf("target/test-classes/one_result/report.json"),
      listOf(file)
    )
    val mockedMetrics = createMockedMetrics(context)
    sensor.execute(context)
    verify(context).newMeasure<Serializable>()
    assertThat(mockedMetrics.first.singleValue).isEqualTo(100.0)
  }

  @Test
  fun shouldAnalyseTwoResults() {
    val file = createMockedInputFile("target/test-classes", "/process.bpmn")
    val context = createMockedSensorContext(
      arrayOf("target/test-classes/two_results/**/report.json"),
      listOf(file)
    )
    val mockedMetrics = createMockedMetrics(context)
    sensor.execute(context)
    assertThat(mockedMetrics.first.singleValue).isEqualTo(100.0)
  }

  @Test
  fun shouldAnalyseMultipleModules() {
    val file = createMockedInputFile("target/test-classes", "/process.bpmn")
    val file2 = createMockedInputFile("target/test-classes", "/process2.bpmn")
    val context = createMockedSensorContext(
      arrayOf("target/test-classes/multiple_projects/**/report.json"),
      listOf(file, file2)
    )
    val mockedMetrics = createMockedMetrics(context)
    sensor.execute(context)
    assertThat(mockedMetrics.first.allValues).contains(100.0, (2.0 / 3.0) * 100.0)
  }

}
