package org.camunda.community.process_test_coverage.sonar

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition

class BpmnQualityProfileTest {

  @Test
  fun should_correctly_define_profile() {
    val profile = BpmnQualityProfile()
    val qualityProfile = mock<BuiltInQualityProfilesDefinition.NewBuiltInQualityProfile> {}
    val context = mock<BuiltInQualityProfilesDefinition.Context> {
      on { createBuiltInQualityProfile(BpmnQualityProfile.NAME, BpmnLanguage.KEY) } doReturn qualityProfile
    }
    profile.define(context)
    verify(qualityProfile).isDefault = true
    verify(qualityProfile).done()
  }

}
