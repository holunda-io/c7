

package org.camunda.community.rest.variables

import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.value.SerializableValue
import org.camunda.bpm.engine.variable.value.SerializationDataFormat
import org.camunda.bpm.engine.variable.value.TypedValue
import org.camunda.community.rest.variables.serialization.CustomValueSerializer

/**
 * Custom value mapper.
 */
@Deprecated(message = "Should use CustomValueSerializer instead", replaceWith = ReplaceWith("CustomValueSerializer",
  "org.camunda.community.rest.variables.serialization.CustomValueSerializer"))
interface CustomValueMapper {

  /**
   * Check method.
   * @param variableValue value.
   * @return `true`of the mapper is responsible.
   */
  fun canHandle(variableValue: Any?): Boolean

  /**
   * Maps the value into a typed value.
   * @param variableValue value.
   * @return typed representation.
   */
  fun mapValue(variableValue: Any?): TypedValue

  /**
   * Serializes the value (still returning the serializable value type).
   * @param variableValue value.
   * @return serialized representation.
   */
  fun serializeValue(variableValue: SerializableValue): SerializableValue

  /**
   * De-serializes the value.
   * @param variableValue serialized value.
   * @return typed value.
   */
  fun deserializeValue(variableValue: SerializableValue): TypedValue

}

/**
 * Backward-compatibility adapter to support deprecated custom value mapper.
 */
@Suppress("DEPRECATION")
class CustomValueMapperAdapter(private val customValueMapper: CustomValueMapper) : CustomValueSerializer {

  // custom value mappers probably only were JSON related
  override val serializationDataFormat: SerializationDataFormat = Variables.SerializationDataFormats.JSON

  override fun serializeValue(value: TypedValue): SerializableValue {
    return customValueMapper.serializeValue(value as SerializableValue)
  }

  override fun deserializeValue(value: SerializableValue): TypedValue {
    return customValueMapper.deserializeValue(value)
  }

  override fun canSerializeValue(value: TypedValue): Boolean {
    return customValueMapper.canHandle(value)
  }

  override fun canDeserializeValue(value: SerializableValue): Boolean {
    return customValueMapper.canHandle(value)
  }


}
