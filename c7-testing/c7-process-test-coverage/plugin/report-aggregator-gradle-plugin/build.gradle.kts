plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish")
}

repositories {
    mavenCentral()
}

val coreClasses: String = findProperty("coreClasses") as String? ?: error("Property 'coreClasses' was not set")
val reportGeneratorClasses: String = findProperty("reportGeneratorClasses") as String? ?: error("Property 'reportGeneratorClasses' was not set")

dependencies {
    implementation(gradleApi())
    implementation(files(coreClasses))
    implementation(files(reportGeneratorClasses))
    implementation(fileTree("target/dependencies") { include("*.jar") })
    testImplementation(gradleTestKit())
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.6")
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("aggregateProcessTestCoverage") {
            id = "io.holunda.c7.c7-process-test-coverage-report-aggregator"
            implementationClass = "org.camunda.community.process_test_coverage.report.aggregator.ReportAggregatorPlugin"
        }
    }
}
