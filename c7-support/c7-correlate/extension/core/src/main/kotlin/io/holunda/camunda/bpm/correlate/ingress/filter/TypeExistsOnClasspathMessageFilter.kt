package io.holunda.camunda.bpm.correlate.ingress.filter

import io.holunda.camunda.bpm.correlate.correlation.metadata.MessageMetaData
import io.holunda.camunda.bpm.correlate.ingress.MessageFilter
import io.holunda.camunda.bpm.correlate.ingress.message.ChannelMessage

/**
 * Accepts all messages having payloads existing on the classpath.
 */
class TypeExistsOnClasspathMessageFilter(
  private val classLoader: ClassLoader? = null
) : MessageFilter {
  override fun <P> accepts(channelMessage: ChannelMessage<P>, metaData: MessageMetaData): Boolean {
    val typeName = metaData.payloadTypeInfo.toFQCN()
    val loader = classLoader ?: Thread.currentThread().contextClassLoader ?: javaClass.classLoader
    return try {
      resolveClass(typeName, loader)
      true
    } catch (e: IllegalAccessError) {
      throw IllegalStateException("Readability mismatch in inheritance hierarchy of class [$typeName]", e)
    } catch (e: LinkageError) {
      false
    } catch (e: ClassNotFoundException) {
      false
    } catch (e: IllegalArgumentException) {
      false
    }
  }

  private fun resolveClass(name: String, loader: ClassLoader?): Class<*> {
    if (name.endsWith("[]")) {
      return resolveClass(name.dropLast(2), loader).arrayType()
    }
    if (name.startsWith("[L") && name.endsWith(";")) {
      return resolveClass(name.substring(2, name.length - 1), loader).arrayType()
    }
    if (name.startsWith("[[")) {
      return resolveClass(name.drop(1), loader).arrayType()
    }
    return try {
      // Checking the classpath must not execute a payload's static initializer.
      Class.forName(name, false, loader)
    } catch (e: ClassNotFoundException) {
      // TypeInfo uses canonical names, whereas the JVM expects '$' for nested classes.
      val separator = name.lastIndexOf('.')
      if (separator == -1) throw e
      Class.forName(name.replaceRange(separator, separator + 1, "$"), false, loader)
    }
  }
}
