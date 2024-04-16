# Camunda Platform 7 Engine API

A small extract of the API layer from the greatest process engine ever.

[![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
[![Camunda 7.20](https://img.shields.io/badge/Camunda%20Version-7.21-orange.svg)](https://docs.camunda.org/manual/7.21/)
[![Build Status](https://github.com/holunda-io/camunda-bpm-api/actions/workflows/default.yml/badge.svg)](https://github.com/holunda-io/camunda-bpm-api/actions/workflows/default.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.camunda-api/camunda-bpm-engine-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.camunda-api/camunda-bpm-engine-api)

## What is this?

Sometimes for building libraries it is useful to have the API only, without the implementation. This library extracts the official Java Camunda Engine API from Camunda JAR and packages it as a small JAR. By doing so, we had to provide very few own classes, to cut the track to implementation. It is by **NO MEANS** a replacement, but just a bunch of classes which may be required on the classpath, if you use a library referencing Camunda BPM classes (but not using them).

## Why do you need this

Imagine you are implementing a remote-communication library like Camunda REST Client Spring Boot that implements the Camunda Plattform 7 Engine API by calling a remote process engine. Or you are building a library that wants to use different artifacts of the Camunda API, without puting them on a compile 
dependency. Then - consider to use this lib...


Short facts:

| Original Library         | Size (mean)  | Dependencies  | API only               | Size (mean) | Dependencies |
| ------------------------ | -----------: | ------------: | ---------------------- | ----------: |-------------:|
| camunda-engine           |    4,7 mb    |    20         | camunda-bpm-engine-api |   225 kb    |            7 |
| camunda-engine-dmn       |    144 kb    |     6         | camunda-dmn-engine-api |    14 kb    |            3 |


## Quick Introduction

### Setup
If you just want to start using the library, put the following dependency into your project `pom.xml`:

```xml
<dependency>
  <groupId>io.holunda.camunda-api</groupId>
  <artifactId>camunda-bpm-engine-api</artifactId>
  <version>7.21.0</version>
</dependency>
```

If you are interested in using the DMN engine API only, put the following dependency into your `pom.xml`:

```xml
<dependency>
    <groupId>io.holunda.camunda-api</groupId>
    <artifactId>camunda-dmn-engine-api</artifactId>
    <version>7.21.0</version>
</dependency>
```


If you are using Gradle Kotlin DSL add to your `build.gradle.kts`:

```kotlin
implementation("io.holunda.camunda-api:camunda-bpm-engine-api:7.21.0")
```

and for DMN only:

```kotlin
implementation("io.holunda.camunda-api:camunda-dmn-engine-api:7.21.0")
```


For Gradle Groovy DSL add to your `build.gradle`:

```groovy
implementation 'io.holunda.camunda-api:camunda-bpm-engine-api:7.21.0'
```

and for DMN only:

```groovy
implementation 'io.holunda.camunda-api:camunda-bpm-engine-api:7.21.0'
```

Please note that the version of this library is tightly-coupled to the version of original Camunda API.  

## Supported version

| Camunda BPM Version | Camunda BPM API Version | 
|---------------------|------------------------:| 
| 7.14                |                  7.14.0 |
| 7.15                |                  7.15.0 |
| 7.16                |                  7.16.0 |
| 7.17                |                  7.17.0 |
| 7.18                |                  7.18.0 |
| 7.19                |                  7.19.0 |
| 7.20                |                  7.20.0 |
| 7.21                |                  7.21.0 |



## License

This library is developed under [Apache License 2](./LICENSE).

## Maintainer

* [Simon Zambrovski](https://gihub.com/zambrovski)
