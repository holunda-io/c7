package io.holunda.commons.immutables._config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.holunda.commons.immutables.CamundaImmutables;
import org.camunda.bpm.engine.form.CamundaFormRef;

import java.io.IOException;

public class FormRefSerializer extends JsonSerializer<CamundaFormRef> {
  @Override
  public void serialize(CamundaFormRef camundaFormRef, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeObject(CamundaImmutables.formRef(camundaFormRef));
  }
}
