package io.holunda.commons.immutables;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonHelper {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static <T> JsonMapper<T> jsonMapper(Class<T> type) {
    return new JsonMapper<>(OBJECT_MAPPER,type);
  }


  public static class JsonMapper<T> {

    private final ObjectMapper om;
    private final Class<T> type;

    public JsonMapper(ObjectMapper om, Class<T> type) {
      this.om = om;
      this.type = type;
    }


    public T fromJson(String json) {
      try {
        return om.readValue(json, type);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

    public String toJson(T value) {
      try {
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(value);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
