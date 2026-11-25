---
title: REST Clock Util
---

# REST Clock Util

`c7-rest-clock-util` provides a JAX-RS resource for reading, setting and resetting
Camunda 7's engine clock through `ClockUtil`. It belongs to the operations
extensions and works independently of Spring Boot.

## Installation

Add the dependency using the same version as the other C7 libraries:

```xml
<dependency>
  <groupId>io.holunda.c7</groupId>
  <artifactId>c7-rest-clock-util</artifactId>
  <version>2026.11.2-SNAPSHOT</version>
</dependency>
```

The application supplies the Camunda engine, a Jakarta JAX-RS runtime (for example
Jersey 3), and a Jackson JSON provider. For Camunda's REST application use
`camunda-engine-rest-core-jakarta` and its existing JSON providers. This module uses
`jakarta.ws.rs`; it cannot be registered unchanged in a legacy `javax.ws.rs` / Jersey 2
application.

## Automatic registration in Jersey

Jersey discovers `ClockAutoDiscoverable` through the JAR's
`META-INF/services/org.glassfish.jersey.internal.spi.AutoDiscoverable` descriptor.
It registers `ClockRestService` in server applications, leaving Jersey clients
unchanged and skipping resources that have already been registered explicitly.
No Spring Boot configuration or custom `ResourceConfig` is required.

This requires Jersey auto-discovery to be enabled (the default). Setting
`jersey.config.disableAutoDiscovery` or `jersey.config.server.disableAutoDiscovery`
to `true` disables automatic registration. Use explicit registration in that case
or with another Jakarta JAX-RS implementation. Automatic registration applies to
each Jersey server application that loads the JAR; `/clock` is relative to each
application's base path.

### Camunda Run Docker image

For a single-file installation, use the artifact with classifier `standalone`.
It bundles the required Kotlin runtime dependencies; Camunda, Jersey, JAX-RS and
Jackson are supplied by Camunda Run. The regular JAR remains available for Maven
applications that resolve their own dependencies. Both artifacts target Java 17.

Build from the repository root:

```sh
./mvnw -pl c7-operations/c7-rest-clock-util -am -DskipExamples verify
mkdir -p extensions
cp c7-operations/c7-rest-clock-util/target/c7-rest-clock-util-*-standalone.jar extensions/
```

Keep only one version of the extension in the mounted directory. With the
[BPM Crafters Camunda Run image](https://github.com/bpm-crafters/process-engine-adapters-camunda-7/tree/develop/docker/camunda-bpm-platform),
mount that directory and enable REST:

```sh
docker run --rm -p 8080:8080 \
  -v "$PWD/extensions:/camunda/extensions:ro" \
  -e CAMUNDA_RUN_REST=true \
  bpmcrafters/camunda-bpm-platform:run-7.24.0
```

Once startup finishes:

```sh
curl --fail http://localhost:8080/engine-rest/clock
```

The image links mounted JARs into Run's `configuration/userlib` during startup.
Restart the container after adding or updating the JAR. No hot deployment is
performed. Mounted files must be readable by the container's UID 1000.

## Explicit registration

Register `io.holunda.camunda.rest.clock.ClockRestService` in the application's
JAX-RS configuration when automatic registration is unavailable or disabled.

For example, a Jersey application can expose Camunda's REST resources and the clock
resource together:

```java
import io.holunda.camunda.rest.clock.ClockRestService;
import jakarta.ws.rs.ApplicationPath;
import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/engine-rest")
public class EngineRestApplication extends ResourceConfig {
  public EngineRestApplication() {
    registerClasses(CamundaRestResources.getResourceClasses());
    registerClasses(CamundaRestResources.getConfigurationClasses());
    register(ClockRestService.class);
  }
}
```

Deploy this application using the servlet container's Jersey integration. If you
already have a `ResourceConfig`, add `register(ClockRestService.class)` to that
configuration instead of creating a second application. In Spring Boot with
Camunda's REST starter, the same call can be added to a custom
`CamundaJerseyResourceConfig` subclass. Spring MVC is not required.

The tests exercise service discovery, disabled discovery, explicit registration,
and the resource in Jersey with Camunda's JSON providers, without a Spring application
context. A packaging test also verifies the standalone JAR and its bundled Kotlin runtime.

## Clock endpoints

`/clock` is relative to the JAX-RS application's base path. With the example above
and no additional servlet context path, it is available at `/engine-rest/clock`.
There is no engine-name segment: the clock is shared by all engines in the JVM.

| Method | Path | Behavior |
| --- | --- | --- |
| GET | `/clock` | Read the current engine time. |
| PUT | `/clock` | Freeze the engine clock at `currentTime`. |
| DELETE | `/clock` | Restore the running system clock. |

All successful responses return HTTP 200 and JSON with an ISO-8601 UTC timestamp:

```json
{"currentTime":"2030-05-01T12:30:00Z"}
```

Read the engine time:

```sh
curl http://localhost:8080/engine-rest/clock
```

Freeze it at a timestamp with a UTC offset or `Z`:

```sh
curl -X PUT http://localhost:8080/engine-rest/clock \
  -H 'Content-Type: application/json' \
  -d '{"currentTime":"2030-05-01T12:30:00Z"}'
```

Missing, null, invalid, or out-of-range timestamps return HTTP 400 without changing
the clock. Times without a UTC offset are invalid. PUT requires `application/json`.

Restore system time:

```sh
curl -X DELETE http://localhost:8080/engine-rest/clock
```

## Scope and access

`ClockUtil` controls a JVM-wide clock: changes affect every Camunda engine in that
JVM, including concurrent requests and tests. It does not change the operating
system clock. Setting a time freezes it until it is changed or reset. Resetting
restores the actual current system time, rather than the previously configured time.

Changing the clock does not execute timer jobs synchronously. Use the job executor
or execute jobs explicitly when you need to verify timer behavior.

The resource adds no authentication or authorization. Apply the application's
access controls to `/clock` when registering it. Camunda's process-engine
permissions do not provide authorization for this JVM-wide operation. In tests,
reset the clock after completion, including after failures.
