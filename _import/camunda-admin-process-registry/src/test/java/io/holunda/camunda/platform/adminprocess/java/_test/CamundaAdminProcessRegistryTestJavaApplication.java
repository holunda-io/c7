package io.holunda.camunda.platform.adminprocess.java._test;

import io.holunda.camunda.bpm.data.CamundaBpmData;
import io.holunda.camunda.platform.adminprocess.AdminProcess;
import io.holunda.camunda.platform.adminprocess.form.BooleanField;
import io.holunda.camunda.platform.adminprocess.form.DateField;
import io.holunda.camunda.platform.adminprocess.form.LongField;
import io.holunda.camunda.platform.adminprocess.form.StringField;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.holunda.camunda.platform.adminprocess.AdminProcess.builder;

@EnableProcessApplication
@SpringBootApplication
public class CamundaAdminProcessRegistryTestJavaApplication {

  private static final Logger logger = LoggerFactory.getLogger(CamundaAdminProcessRegistryTestJavaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CamundaAdminProcessRegistryTestJavaApplication.class, args);
  }

  @Bean
  AdminProcess helloWorldAdminProcess() {

    var stringField = new StringField("fooId", "Foo - enter your name");
    var dateField = new DateField("dateId", "Date - select some magic");
    var numberField = new LongField("longId", "A number");
    var booleanField = new BooleanField("booleanId", "Yes or no?");

    return builder("helloWorldJava")
      .label("Hello World Java")
      .addFormField(stringField)
      .addFormField(dateField)
      .addFormField(numberField)
      .addFormField(booleanField)
      .delegate(execution -> {
        var reader = CamundaBpmData.reader(execution);

        logger.info(" Hi, I am the process running with:");
        logger.info("foo: {}", reader.get(stringField));
        logger.info("date: {}", reader.get(dateField));
        logger.info("number: {}", reader.get(numberField));
        logger.info("yes: {}", reader.get(booleanField));
      })
      .build();
  }
}
