package org.camunda.community.rest.itest.boot3

import org.junit.platform.suite.api.ExcludePackages
import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

@Suite
@SuiteDisplayName("SpringBoot 3.x Integration Tests")
@SelectPackages("org.camunda.community.rest.itest")
@ExcludePackages("org.camunda.community.rest.itest.boot3")
class SpringBoot3SuiteITest
