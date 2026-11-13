---
title: Test REST API
---

# Test REST API

`c7-test-rest-api` adds opt-in HTTP endpoints for testing Camunda 7 applications.
The initial endpoint controls Camunda's engine clock through `ClockUtil`.

## Installation

Requires a Camunda 7 engine and a Spring Boot 3 servlet application with Spring MVC
(`spring-boot-starter-web`). Add the dependency using the same version as the other C7 libraries:

```xml
<dependency>
  <groupId>io.holunda.c7</groupId>
  <artifactId>c7-test-rest-api</artifactId>
  <version>2026.11.2-SNAPSHOT</version>
</dependency>
```

Enable it in your test or local development configuration:

```yaml
camunda:
  bpm:
    test-rest:
      enabled: true
```

The endpoints are disabled by default. Auto-configuration requires Spring MVC and
Camunda on the classpath and a servlet web application.

## Clock endpoints

The path is relative to the application's servlet context, separate from Camunda's
`/engine-rest` API. The Camunda REST starter is not required.

| Method | Path | Behavior |
| --- | --- | --- |
| GET | `/camunda-test/clock` | Read the current engine time. |
| PUT | `/camunda-test/clock` | Freeze the engine clock at `currentTime`. |
| DELETE | `/camunda-test/clock` | Restore the running system clock. |

All successful responses return HTTP 200 and JSON with an ISO-8601 UTC timestamp:

```json
{"currentTime":"2030-05-01T12:30:00Z"}
```

Read the engine time:

```sh
curl http://localhost:8080/camunda-test/clock
```

Freeze it at a timestamp with a UTC offset or `Z`:

```sh
curl -X PUT http://localhost:8080/camunda-test/clock \
  -H 'Content-Type: application/json' \
  -d '{"currentTime":"2030-05-01T12:30:00Z"}'
```

Missing, null, invalid, or out-of-range timestamps return HTTP 400 without changing
the clock. Times without a UTC offset are invalid.

Restore system time when the test finishes, including after failures:

```sh
curl -X DELETE http://localhost:8080/camunda-test/clock
```

## Scope and test isolation

`ClockUtil` controls a JVM-wide clock: changes affect every Camunda engine in that
JVM, including concurrent requests and tests. It does not change the operating
system clock. Setting a time freezes it until it is changed or reset. Resetting
restores the actual current system time, rather than the previously configured time.

Changing the clock does not execute timer jobs synchronously. Use the job executor
or execute jobs explicitly when your test needs to verify timer behavior.

These endpoints are for isolated development and test environments. They add no
authentication; configure application security if access needs to be restricted.
Camunda REST API authentication filters scoped to `/engine-rest/*` do not protect
this separate path. Do not enable this extension in production.
