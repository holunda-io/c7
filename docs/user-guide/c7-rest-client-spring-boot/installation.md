The library is divided into different modules which allow you to use parts which you need.

## Client only

If you are interested in a complete REST client, you can use the following modules:

- c7-rest-client-spring-boot-openapi: contains the OpenAPI generated Open Feign client classes (without configuration).
- c7-rest-client-spring-boot-feign: configures Open Feign Client for Spring Boot 3.5.x.
- c7-rest-client-spring-boot-feign-4: configures Open Feign Client for Spring Boot 4.x.x.
- c7-rest-client-variables: variable serialization and mapping for REST access.

## Camunda Java API Implementation

If you are interested in out implementation Camunda Java API by the REST client, please use the following libraries:

- c7-rest-client-spring-boot-core: implements Camunda Java API by delegating to REST
- c7-rest-client-spring-boot-starter: configures Camunda API Client for Spring Boot 3.5.x for client applications.
- c7-rest-client-spring-boot-starter-4: configures Camunda API Client for Spring Boot 4.x.x for client applications.
- c7-rest-client-spring-boot-starter-provided: configures Camunda API Client for Spring Boot 3.5.x for process applications.
- c7-rest-client-spring-boot-starter-4-provided: configures Camunda API Client for Spring Boot 4.x.x for process applications.

## External dependencies

The library is neither providing Spring Boot nor other the dependencies.
If you are interested in providing them, make sure to use BOM imports:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-dependencies</artifactId>
      <version>${spring-cloud.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>${spring-boot.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

| Version   | Spring Boot | Spring Cloud |
|-----------|-------------|--------------|
| 2026.04.2 | 4.x.x       | 2025.1.1     |
| 2026.04.2 | 3.5.x       | 2025.0.0     |
| 2025.12.2 | 4.x.x       | 2025.1.0     |
| 2025.12.2 | 3.5.x       | 2025.0.0     |
