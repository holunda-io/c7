# C7 - holunda.io extensions

## Modules

* tbd

## build

We rely on the camunda-ee artifacts, that's why we have the `camunda-bpm-ee` repository defined in the root pom.xml.
When building with `mvn` you will have to provide your camunda credentials.

This can be done via `.m2/settings.xml` or a local `.mvn/settings.xml` file, in which case you have to run the maven
buidl with the `-s .mvn/settings.xml` option:.


