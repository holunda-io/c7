package org.camunda.community.process_test_coverage.report;

import org.camunda.community.process_test_coverage.core.model.DefaultCollector;
import org.camunda.community.process_test_coverage.core.model.Suite;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.community.process_test_coverage.report.CoverageReportUtil.TARGET_DIR_ROOT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test is isolated not to infer with modified test.
 */
class CoverageReportUtilTest {

  @Test
  void get_default_report_path() {
    assertThat(TARGET_DIR_ROOT).isEqualTo("target/process-test-coverage/");
  }

  @Test
  void create_report_should_write_report_file_in_default_directory() {
    String suiteId = "html";
    DefaultCollector collector = mock(DefaultCollector.class);
    Suite suite = new Suite(suiteId, "name");
    when(collector.getActiveSuite()).thenReturn(suite);
    CoverageReportUtil.createReport(collector, null);
    assertThat(Path.of(TARGET_DIR_ROOT, suiteId))
      .exists()
      .isDirectoryContaining("glob:**.png")
      .isDirectoryContaining(path -> path.endsWith("report.html"))
      .isDirectoryNotContaining(path -> path.endsWith("report.json"));
  }

  @Test
  void create_report_should_write_report_file_in_custom_directory() {
    String suiteId = "html";
    String reportDirectory = "target/custom-dir";
    DefaultCollector collector = mock(DefaultCollector.class);
    Suite suite = new Suite(suiteId, "name");
    when(collector.getActiveSuite()).thenReturn(suite);
    CoverageReportUtil.createReport(collector, reportDirectory);
    assertThat(Path.of(reportDirectory, suiteId))
      .exists()
      .isDirectoryContaining("glob:**.png")
      .isDirectoryContaining(path -> path.endsWith("report.html"));
  }

  @Test
  void create_json_report_should_write_report_file_in_default_directory() {
    String suiteId = "json";
    DefaultCollector collector = mock(DefaultCollector.class);
    Suite suite = new Suite(suiteId, "name");
    when(collector.getActiveSuite()).thenReturn(suite);
    CoverageReportUtil.createJsonReport(collector, null);
    assertThat(Path.of(TARGET_DIR_ROOT, suiteId))
      .exists()
      .isDirectoryNotContaining("glob:**.png")
      .isDirectoryNotContaining(path -> path.endsWith("report.html"))
      .isDirectoryContaining(path -> path.endsWith("report.json"));
  }

}
