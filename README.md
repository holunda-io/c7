
# C7 - holunda.io extensions

> One-stop monorepo for all holunda libraries in Camunda 7 context.

[![Build Status](https://github.com/holunda-io/c7/workflows/Development%20branches/badge.svg)](https://github.com/holunda-io/c7/actions)
[![sponsored](https://img.shields.io/badge/sponsoredBy-Holisticon-RED.svg)](https://holisticon.de/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.c7._/c7-root/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.c7._/c7-root)
![Compatible with: Camunda Platform 7](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)
[![codecov](https://codecov.io/gh/holunda-io/c7/branch/develop/graph/badge.svg?token=imlMg9vhLZ)](https://codecov.io/gh/holunda-io/c7)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8f4d11e919354d9eaa9be3be7b1a4232)](https://app.codacy.com/gh/holunda-io/c7/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/8f4d11e919354d9eaa9be3be7b1a4232)](https://app.codacy.com/gh/holunda-io/c7/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)

## What is it?

In the past decade we developed a lot of libraries and extensions for Camunda 7 process engine. In doing so we improved the overall
usability of the engine as a **developer**, **tester** or as an **operator** of the process application. This mono-repo now contains all these libraries
and is the new source for all those libraries. The old repositories are already archived or will be archived soon and the entire development
will be continued from this repository. We will support these libraries further but will release them using a single version from this 
repository.

## Modules

### Support

Includes libraries built as improvements to Camunda 7 engine itself.

- camunda-bpm-correlate: [![incubating](https://img.shields.io/badge/lifecycle-INCUBATING-orange.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-bpm-correlate/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-bpm-correlate)
- camunda-bpm-data [![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.data/camunda-bpm-data/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.data/camunda-bpm-data)

- camunda-bpm-spring-boot-deployment [![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.deployment/camunda-bpm-spring-boot-deployment/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.deployment/camunda-bpm-spring-boot-deployment)

- camunda-platform-7-custom-batch (coming soon)

### Operations

Includes extensions used for improved operations of Camunda 7.

- camunda-api [![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.camunda-api/camunda-bpm-engine-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.camunda-api/camunda-bpm-engine-api)

- c7-commons-immutables [![incubating](https://img.shields.io/badge/lifecycle-INCUBATING-orange.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.commons/camunda-commons-immutables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.commons/camunda-commons-immutables)

- camunda-platform-7-autologin [![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-platform-7-autologin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-platform-7-autologin)

- camunda-admin-process-registry [![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-admin-process-registry/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda/camunda-admin-process-registry)

- camunda-platform-7-rest-client-spring-boot (coming soon)

### Testing

Includes features for testing.

- camunda-bpm-jgiven [![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)
  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.testing/camunda-bpm-jgiven/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.testing/camunda-bpm-jgiven)
- camunda-platform-7-mockito (coming soon)
- camunda-process-tet-coverage (coming soon)


## Built for Camunda CE and Camunda EE

Currently, the libs are built using camunda-ce artifacts, but after October 2025, at the EOL of Camunda CE we will switch
to Camunda EE. From then on, as we rely on the camunda-ee artifacts, that's why we have the `camunda-bpm-ee` repository 
defined in the root pom.xml. When building with `mvn` you will have to provide your camunda credentials.

This can be done via `.m2/settings.xml` or a local `.mvn/settings.xml` file, in which case you have to run the maven
build with the `-s .mvn/settings.xml` option.


## License

[![Apache License 2](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

This library is developed under Apache 2.0 License.

