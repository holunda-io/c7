# Spring Boot dependency management

The reactor provides two BOMs:

| BOM | Spring Boot | Spring Cloud |
| --- | --- | --- |
| `io.holunda.c7._:c7-spring-boot-3-bom` | 3.5.16 | 2025.0.3 |
| `io.holunda.c7._:c7-spring-boot-4-bom` | 4.2.0 | 2026.0.0 |

Both inherit the shared dependency management from `c7-parent`. They manage versions without adding dependencies or configuring build plugins.

Import the appropriate BOM in a module's `dependencyManagement`:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.holunda.c7._</groupId>
      <artifactId>c7-spring-boot-3-bom</artifactId>
      <version>2026.11.2-SNAPSHOT</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

For Boot 4, use `c7-spring-boot-4-bom` instead. Within this repository, use `${project.version}` for the BOM version.

Modules targeting Boot 3 import `c7-spring-boot-3-bom` explicitly. The REST client and its examples use a local `${spring-boot-bom.artifactId}` property because each parent shares its import with both Boot 3 and Boot 4 children. Those parents default to Boot 3, and their Boot 4 children override the property. The regression-test parent also defines its selection locally so compatibility profiles can switch to upstream BOMs. There is no default BOM selection in `c7-parent`.

BOM imports do not expose their properties to consumers. Boot and Cloud version properties are defined only in the two BOMs. Kotlin annotation processing resolves its version from the declared, BOM-managed `spring-boot-configuration-processor` dependency. The examples pin the Maven plugin independently using `spring-boot-maven-plugin.version`.

Spring Framework integrations also import the corresponding Boot BOM, which manages their Framework dependencies without adding Boot runtime dependencies. Coverage regression tests use the local Boot 3 BOM by default; their older compatibility profiles select the upstream Boot BOM and the required historical version explicitly.
