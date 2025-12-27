package org.camunda.community.process_test_coverage.sonar

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition


class BpmnQualityProfile : BuiltInQualityProfilesDefinition {

  companion object {
    const val NAME = "Sonar way"
  }

  override fun define(context: BuiltInQualityProfilesDefinition.Context) {
    val profile = context.createBuiltInQualityProfile(NAME, BpmnLanguage.KEY)
    profile.isDefault = true
    profile.done()
  }
}
