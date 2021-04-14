package io.holunda.example.camunda.autologin;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaAutologinExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamundaAutologinExampleApplication.class, args);
    }
}
