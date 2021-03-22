[![Build Status](https://github.com/holunda-io/camunda-bpm-api/actions/workflows/default.yml/badge.svg)](https://github.com/holunda-io/camunda-bpm-api/actions/workflows/default.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-bpm-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-bpm-api)

## Camunda BPM Engine API

> A small extract of the API layer from the greatest process engine ever.

## What is this?

Sometimes for building libraries it is useful to have the API only, without the implementation. This library extracts the official Java Camunda Engine API 
from Camunda JAR and packages it as a small JAR. By doing so, we had to provide very few own classes, to cut the track to implementation. It is by **NO MEANS** a replacement, but just a bunch of classes which may be required on the classpath, if 
you use a library referencing Camunda BPM classes (but not using them).

## Quick Introduction

### Setup
If you just want to start using the library, put the following dependency into your project `pom.xml`:

```xml
<dependency>
  <groupId>io.holunda</groupId>
  <artifactId>camunda-bpm-api</artifactId>
  <version>7.14</version>
</dependency>
```

If you are using Gradle Kotlin DSL add to your `build.gradle.kts`:

```kotlin
implementation("io.holunda:camunda-bpm-api:7.14")
```

For Gradle Groovy DSL add to your `build.gradle`:

```groovy
implementation 'io.holunda:camunda-bpm-api:7.14'
```

Please note that the version of this library is tightly-coupled to the version of original Camunda API.  

## License

This library is developed under [Apache License 2](./LICENSE).

## Maintainer

* [Simon Zambrovski](https://gihub.com/zambrovski)
