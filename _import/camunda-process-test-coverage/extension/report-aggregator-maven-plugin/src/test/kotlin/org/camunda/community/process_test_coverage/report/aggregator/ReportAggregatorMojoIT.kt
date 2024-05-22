/*-
 * #%L
 * Camunda Process Test Coverage Report Aggregator Maven Plugin
 * %%
 * Copyright (C) 2019 - 2024 Camunda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.camunda.community.process_test_coverage.report.aggregator

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.soebes.itf.extension.assertj.MavenITAssertions.assertThat
import com.soebes.itf.jupiter.extension.MavenJupiterExtension
import com.soebes.itf.jupiter.extension.MavenTest
import com.soebes.itf.jupiter.maven.MavenExecutionResult
import java.util.function.Consumer
import kotlin.io.path.readText

@MavenJupiterExtension
class ReportAggregatorMojoIT {

    @MavenTest
    fun one_result(result: MavenExecutionResult) {
        assertThat(result).isSuccessful
            .project()
            .hasTarget()
            .withFile("process-test-coverage/all/report.json")
            .exists()
            .isRegularFile()
            .hasSameTextualContentAs(result.mavenProjectResult.targetProjectDirectory.resolve(
                "target/process-test-coverage/test.FirstTest/report.json"))
    }

    @MavenTest
    fun two_results(result: MavenExecutionResult) {
        assertThat(result).isSuccessful
            .project()
            .hasTarget()
            .withFile("process-test-coverage/all/report.json")
            .exists()
            .isRegularFile()
            .content()
            .satisfies(Consumer {
                val actual = Gson().fromJson(it, JsonObject::class.java)
                val expected = Gson().fromJson(
                    result.mavenProjectResult.targetProjectDirectory.resolve("expected_result.json").readText(),
                    JsonObject::class.java
                )
                assertThat(actual.getAsJsonArray("suites")).containsExactlyInAnyOrderElementsOf(
                    expected.getAsJsonArray("suites"))
                assertThat(actual.getAsJsonArray("models")).containsExactlyInAnyOrderElementsOf(
                    expected.getAsJsonArray("models"))
            })
    }

    @MavenTest
    fun multiple_projects(result: MavenExecutionResult) {
        assertThat(result).isSuccessful
            .project()
            .hasTarget()
            .withFile("process-test-coverage/all/report.json")
            .exists()
            .isRegularFile()
            .content()
            .satisfies(Consumer {
                val actual = Gson().fromJson(it, JsonObject::class.java)
                val expected = Gson().fromJson(
                    result.mavenProjectResult.targetProjectDirectory.resolve("expected_result.json").readText(),
                    JsonObject::class.java
                )
                assertThat(actual.getAsJsonArray("suites")).containsExactlyInAnyOrderElementsOf(
                    expected.getAsJsonArray("suites"))
                assertThat(actual.getAsJsonArray("models")).containsExactlyInAnyOrderElementsOf(
                    expected.getAsJsonArray("models"))
            })
    }

}
