package io.holunda.camunda.bpm.correlate

import io.holunda.camunda.bpm.correlate.resources.CamundaBpmCorrelateCockpitPluginRootResource
import io.github.oshai.kotlinlogging.KotlinLogging
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource
import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin
import org.springframework.context.ApplicationContext
import kotlin.reflect.KClass

private val logger = KotlinLogging.logger {}
/**
 * Main plugin class.
 */
class CamundaBpmCorrelateCockpitPlugin : AbstractCockpitPlugin() {

  companion object {
    const val PREFIX = "io.holunda.camunda.bpm.correlate"
    const val ID = "correlate-cockpit-plugin"
  }

  init {
    logger.info { "[Camunda CORRELATE] Cockpit plugin activated." }
  }

  override fun getId(): String = ID

  override fun getResourceClasses(): Set<Class<*>> = hashSetOf<Class<*>>(
    CamundaBpmCorrelateCockpitPluginRootResource::class.java
  )

  override fun getMappingFiles(): List<String> {
    return listOf()
  }
}

/**
 * Retrieves spring application context via static in-JVM hack.
 * @return spring application context.
 */
fun getApplicationContext(): ApplicationContext {
  return requireNotNull(CamundaBpmCorrelateConfiguration.applicationContext) { "Spring application context could not be found." }
}

/**
 * Retrieves a bean, works like injection.
 */
inline fun <reified T : Any> AbstractCockpitPluginResource.getBean(clazz: KClass<T>): T {
  return getApplicationContext().getBean(clazz.java)
}

/**
 * Retrieves a bean, works like injection.
 */
inline fun <reified T : Any> AbstractCockpitPluginRootResource.getBean(clazz: KClass<T>): T {
  return getApplicationContext().getBean(clazz.java)
}
