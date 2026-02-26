---
title: C7 JGiven User Guide
---

# camunda-platform-7-custom-batch

The goal of this camunda extension is to provide an simple way of using the camunda batch functionality.
Camunda Batch could be used to split a huge workload into small asynchronous jobs.
With this extension, we want to open the camunda batch functionality to everyone.

## Why should I use this extension

Camunda batch is really cool for offloading huge workload into small asynchronous pieces of work. E.g.:

* Unclaiming / Updating a huge list of camunda tasks
* Call APIs with batches of data
* Distribution of emails
* Technical stuff like clean-up

## Get started

The extension will be published on maven central, so if you are using maven, just add the dependency:

Maven Users:

```xml
<dependency>
  <groupId>io.holunda.c7</groupId>
  <artifactId>c7-custom-batch-core</artifactId>
</dependency>
```

Gradle Users:

```kotlin
compile("io.holunda.c7:c7-custom-batch-core")
```

First you have to define an own job handler for working on the single batch data:

```java
@Component
public class PrintStringBatchJobHandler extends CustomBatchJobHandler<String> {
  @Override
  public void execute(List<String> data, CommandContext commandContext) {
      data.forEach(dataEntry -> logger.info("Work on data entry: " + dataEntry));
  }

  @Override
  public String getType() {
      return "print-string-batch-handler";
  }
}
```

Next you have to notify the engine about this job handler, e.g. with spring-boot:

```java
@Bean
public ProcessEnginePlugin customBatchHandlerPlugin(PrintStringBatchJobHandler printStringBatchJobHandler) {
  return CustomBatchHandlerPlugin.of(printStringBatchJobHandler);
}
```

Finally, the creation of the batch itself:

```java
CustomBatchBuilder.of(listOfStringData)
  .jobHandler(printStringBatchJobHandler)
  .create();
```

Or with more configuration:

```java
CustomBatchBuilder.of(listOfStringData)
  .configuration(engineConfiguration)
  .jobHandler(printStringBatchJobHandler)
  .jobsPerSeed(10)
  .jobPriority(0L)
  .invocationsPerBatchJob(5)
  .exclusive(true)
  .create(engineConfiguration.getCommandExecutorTxRequired());
```

Note: The batch `jobPriority` is only considered when using Job Executor with the corresponding Acquisition Strategy `jobExecutorAcquireByPriority`. (see [camunda documentation](https://docs.camunda.org/manual/latest/user-guide/process-engine/the-job-executor/#job-acquisition))
The seed and monitor jobs receive the same priority as the batch.

## Resources

* [User Guide](./extension/README.adoc)
* [Issue Tracker](https://github.com/camunda-community-hub/camunda-platform-7-custom-batch/issues)
* [Discussion Forum](https://forum.camunda.org/c/community-extensions/custom-batch)
* [Camunda Batch Docs](https://docs.camunda.org/manual/latest/user-guide/process-engine/batch/)
