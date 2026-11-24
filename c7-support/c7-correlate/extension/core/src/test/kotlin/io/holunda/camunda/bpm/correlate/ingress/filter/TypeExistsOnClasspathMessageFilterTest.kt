package io.holunda.camunda.bpm.correlate.ingress.filter

import io.holunda.camunda.bpm.correlate.PayloadType
import io.holunda.camunda.bpm.correlate.correlation.metadata.TypeInfo
import io.holunda.camunda.bpm.correlate.emptyMessage
import io.holunda.camunda.bpm.correlate.emptyMessageMetadata
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TypeExistsOnClasspathMessageFilterTest {

  @Test
  fun `should accept any message for a class on a classpath`() {
    val filter = TypeExistsOnClasspathMessageFilter()
    assertThat(filter.accepts(emptyMessage(), emptyMessageMetadata().copy(payloadTypeInfo = TypeInfo.fromClass(PayloadType::class)))).isTrue
    assertThat(filter.accepts(emptyMessage(), emptyMessageMetadata().copy(payloadTypeInfo = TypeInfo.fromFQCN("not.existing.package.NotExistingClass")))).isFalse
  }

  @ParameterizedTest
  @ValueSource(strings = [
    "java.lang.String", "java.util.Map.Entry", "java.util.Map\$Entry",
    "java.lang.String[]", "java.lang.String[][]", "java.util.Map.Entry[]",
    "[Ljava.lang.String;", "[[Ljava.lang.String;", "[Ljava.util.Map.Entry;"
  ])
  fun `should accept canonical and JVM type names`(name: String) {
    assertThat(accepts(TypeExistsOnClasspathMessageFilter(), name)).isTrue
  }

  @ParameterizedTest
  @ValueSource(strings = ["missing.Payload", "missing.Payload[]", "[Lmissing.Payload;"])
  fun `should reject missing payload types`(name: String) {
    assertThat(accepts(TypeExistsOnClasspathMessageFilter(), name)).isFalse
  }

  @Test
  fun `should use the supplied class loader`() {
    assertThat(accepts(TypeExistsOnClasspathMessageFilter(rejectingLoader()), PayloadType::class.java.name)).isFalse
  }

  @Test
  fun `should use the thread context class loader by default`() {
    val thread = Thread.currentThread()
    val original = thread.contextClassLoader
    try {
      thread.contextClassLoader = rejectingLoader()
      assertThat(accepts(TypeExistsOnClasspathMessageFilter(), PayloadType::class.java.name)).isFalse
      assertThat(accepts(TypeExistsOnClasspathMessageFilter(javaClass.classLoader), PayloadType::class.java.name)).isTrue
    } finally {
      thread.contextClassLoader = original
    }
  }

  @Test
  fun `should fall back to its own loader when there is no context loader`() {
    val thread = Thread.currentThread()
    val original = thread.contextClassLoader
    try {
      thread.contextClassLoader = null
      assertThat(accepts(TypeExistsOnClasspathMessageFilter(), PayloadType::class.java.name)).isTrue
    } finally {
      thread.contextClassLoader = original
    }
  }

  @Test
  fun `should reject payloads with missing dependencies`() {
    val loader = rejectingLoader { NoClassDefFoundError("missing.Dependency") }
    assertThat(accepts(TypeExistsOnClasspathMessageFilter(loader), PayloadType::class.java.name)).isFalse
  }

  @Test
  fun `should report module readability errors`() {
    val loader = rejectingLoader { IllegalAccessError("unreadable module") }
    assertThatThrownBy { accepts(TypeExistsOnClasspathMessageFilter(loader), PayloadType::class.java.name) }
      .isInstanceOf(IllegalStateException::class.java)
      .hasCauseInstanceOf(IllegalAccessError::class.java)
  }

  @Test
  fun `should not initialize payload classes`() {
    assertThat(accepts(TypeExistsOnClasspathMessageFilter(), PayloadWithInitializer::class.java.canonicalName)).isTrue
    assertThat(PayloadInitialization.initialized).isFalse
  }

  private fun accepts(filter: TypeExistsOnClasspathMessageFilter, name: String): Boolean =
    filter.accepts(emptyMessage(), emptyMessageMetadata().copy(payloadTypeInfo = TypeInfo.fromFQCN(name)))

  private fun rejectingLoader(failure: () -> Throwable = { ClassNotFoundException() }): ClassLoader =
    object : ClassLoader(javaClass.classLoader) {
      override fun loadClass(name: String): Class<*> {
        if (name == PayloadType::class.java.name) throw failure()
        return super.loadClass(name)
      }
    }

  class PayloadWithInitializer {
    companion object {
      init {
        PayloadInitialization.initialized = true
      }
    }
  }
}

private object PayloadInitialization {
  var initialized = false
}
