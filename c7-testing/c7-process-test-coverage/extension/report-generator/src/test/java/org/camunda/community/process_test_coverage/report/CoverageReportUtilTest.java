package org.camunda.community.process_test_coverage.report;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test is isolated not to infer with modified test.
 */
public class CoverageReportUtilTest {

  @Test
  public void get_default_report_path() {
    assertThat(CoverageReportUtil.TARGET_DIR_ROOT).isEqualTo("target/process-test-coverage/");
  }

}
