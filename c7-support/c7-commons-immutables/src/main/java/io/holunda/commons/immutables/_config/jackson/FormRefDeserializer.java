package io.holunda.commons.immutables._config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import io.holunda.commons.immutables.form.ImmutableFormRef;
import org.camunda.bpm.engine.form.CamundaFormRef;

import java.io.IOException;

public class FormRefDeserializer extends JsonDeserializer<CamundaFormRef>  {
  @Override
  public CamundaFormRef deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    return jsonParser.readValueAs(ImmutableFormRef.class);
  }
}
