[cols="a,a,a,a"]
|===
| // ci
image::https://github.com/holunda-io/camunda-bpm-api/workflows/default/badge.svg[caption="Build Status", link=https://github.com/holunda-io/camunda-bpm-api/actions]
| // maven central
image::https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-bpm-api/badge.svg[caption="Maven Central", link=https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-bpm-api]
| // license
image::https://img.shields.io/badge/License-Apache%202.0-blue.svg[caption="License", link="https://www.holunda.io/camunda-bpm-api/license"]
| // changelog
image::https://img.shields.io/badge/CHANGES----blue.svg[caption="Change log" link="https://www.holunda.io/camunda-bpm-api/changelog"]
|===

## Camunda BPM Engine API

> A small extract of the API layer from the greates process engine ever.

## What is this?

Sometimes for building libraries it is useful to have the API only, without the implementation. This library extracts the official Java Camunda Engine API from Camunda JAR and packages it as a small JAR.

## Quick Introduction

### Setup
If you just want to start using the library, put the following dependency into your project `pom.xml`:

[source,xml]
----
<dependency>
  <groupId>io.holunda</groupId>
  <artifactId>camunda-bpm-api</artifactId>
  <version>7.14</version>
</dependency>
----

## License

This library is developed under [Apache License 2].

## Maintainer

* link:https://gihub.com/zambrovski[Simon Zambrovski]