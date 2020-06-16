# camunda-immutables

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.holunda.commons/camunda-commons-immutables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.holunda.commons/camunda-commons-immutables)
[![Java CI with Maven](https://github.com/holunda-io/camunda-commons-immutables/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/holunda-io/camunda-commons-immutables/actions?query=workflow%3A%22Java+CI+with+Maven%22)

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

