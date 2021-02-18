package io.holunda.commons.immutables._config;

import org.immutables.value.Value;

/**
 * This annotation controls how immutables generates the data classes from the interfaces.
 *
 * TODO: we do not want to ship this in the jar
 */
@Value.Style(
  // Detect names starting with underscore
  typeAbstract = "_*",
  defaultAsDefault = true,
  // Make generated public, leave underscored as package private
  visibility = Value.Style.ImplementationVisibility.PUBLIC,
  // Seems unnecessary to have builder or superfluous copy method
  defaults = @Value.Immutable(
    builder = true, copy = true, prehash = true
  ),
  jdkOnly = true
)
public @interface CamundaPojoStyle {
  // empty
}
