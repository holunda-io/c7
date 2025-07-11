## Why should I use this?

You are developing an application and need to build a small function called by the ops or by developers directly 
from the Camunda 7 tool stack available? You might create a REST resource, deploy swagger-ui, configure security,
provide good examples and instructions for the user... Or you implement a simple function and deploy it as a 
simple one-service process start-able from the Camunda Tasklist. 

## How to start?

### Install dependency

Add the following dependency to your project classpath:

```xml
<dependency>
  <groupId>io.holunda</groupId>
  <artifactId>camunda-admin-process-registry</artifactId>
</dependency>

```

### Define a Bean Factory for your Admin Process

Define a Bean Factory for an Admin Process Bean like this:

```kotlin
  import io.holunda.camunda.platform.adminprocess.AdminProcess


  @Bean
  fun helloWorldAdminProcess(): AdminProcess {
    
    val foo = StringField("foo", "Foo - enter your name")
    val date = DateField("date", "Date - select some magic")

    return adminProcess(
      activityId = "helloWorld",
      label = "Hello World 2",
      formFields = listOf(foo, date)
    ) {
      
      val variables = CamundaBpmData.reader(it)

      logger.info { """ Hi, I am the process running with:
          * foo: ${variables.get(foo)}
          * date: ${variables.get(date)}
        """.trimIndent()
      }
    }
  }
  
```
