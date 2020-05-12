package io.holunda.commons.immutables;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.Test;

public class CamundaImmutablesTest {

  public static String uuid() {
    return UUID.randomUUID().toString();
  }


  public interface BasicImmutableTest {

     void create_minimal_dto();

     void convert_json();
  }
}
