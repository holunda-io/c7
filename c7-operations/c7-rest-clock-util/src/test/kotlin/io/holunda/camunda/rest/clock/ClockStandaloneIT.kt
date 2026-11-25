package io.holunda.camunda.rest.clock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.net.URLClassLoader
import java.nio.file.Path
import java.time.Instant
import java.util.jar.JarFile

class ClockStandaloneIT {

  @Test
  fun `standalone jar includes discovery metadata and Kotlin but leaves host libraries out`() {
    val artifact = Path.of(System.getProperty("clock.standalone.jar"))
    JarFile(artifact.toFile()).use { jar ->
      val service = jar.getJarEntry("META-INF/services/org.glassfish.jersey.internal.spi.AutoDiscoverable")
      assertThat(service).isNotNull()
      assertThat(jar.getInputStream(service).bufferedReader().use { it.readText().trim() })
        .isEqualTo("io.holunda.camunda.rest.clock.ClockAutoDiscoverable")
      assertThat(jar.getJarEntry("io/holunda/camunda/rest/clock/ClockAutoDiscoverable.class")).isNotNull()
      val entries = jar.entries().asSequence().map { it.name }.toList()
      assertThat(entries).noneMatch {
        it.startsWith("org/camunda/") || it.startsWith("org/glassfish/jersey/") ||
          it.startsWith("jakarta/ws/rs/") || it.startsWith("com/fasterxml/jackson/") ||
          it.startsWith("org/springframework/")
      }
    }
    // The platform parent cannot supply Kotlin from the test classpath.
    URLClassLoader(arrayOf(artifact.toUri().toURL()), ClassLoader.getPlatformClassLoader()).use { loader ->
      val timestamp = Instant.parse("2030-05-01T12:30:00Z")
      val dto = loader.loadClass("io.holunda.camunda.rest.clock.ClockTime")
      val instance = dto.getConstructor(Instant::class.java).newInstance(timestamp)
      assertThat(dto.getMethod("getCurrentTime").invoke(instance)).isEqualTo(timestamp)
      assertThat(loader.loadClass("kotlin.jvm.internal.Intrinsics").classLoader).isSameAs(loader)
    }
  }
}
