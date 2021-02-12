# camunda-immutables

[![Build Status](https://github.com/holunda-io/camunda-commons-immutables/workflows/Development%20branches/badge.svg)](https://github.com/holunda-io/camunda-commons-immutables/actions)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.commons/camunda-commons-immutables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.commons/camunda-commons-immutables)
[![sponsored](https://img.shields.io/badge/sponsoredBy-Holisticon-RED.svg)](https://holisticon.de/)

Immutable implementations of camunda api interfaces.

## Supported types

* ActivityInstance
* Attachment
* Batch
* CaseExecution
* Comment
* EventSubscription
* Execution
* IdentityLink
* Incident
* Group
* Job
* ProcessElementInstance
* ProcessInstance
* Task
* Tenant
* User

## Development

To release run:

`./mvnw gitflow:release-start`

`./mvnw gitflow:release-finish`

