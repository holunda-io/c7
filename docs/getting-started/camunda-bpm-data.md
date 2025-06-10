## Why should I use this?

If you are a software engineer and run process automation projects in your company or on behalf of the customer
based on Camunda Process Engine, you probably are familiar with process variables. Camunda offers an API to access
them and thereby manipulate the state of the process execution - one of the core features during process automation.

Unfortunately, as a user of the Camunda API, you have to exactly know the variable type (so the Java class behind it).
For example, if you store a String in a variable `"orderId"` you must extract it as a String in every piece of code.
Since there is no code connection between the different code parts, but the BPMN process model orchestrates
these snippets to a single process execution, it makes refactoring and testing of process automation projects
error-prone and challenging.

This library helps you to overcome these difficulties and make access, manipulation and testing process variables really
easy and convenient. We leverage the Camunda API and offer you not only a better API but also some
[additional features](../user-guide/camunda-bpm-data/features.md).

## How to start?

### Add dependency

Current version available in Sonatype OSS Maven Central is:

In Apache Maven add to your `pom.xml`:

```xml
<dependency>
  <groupId>io.holunda.data</groupId>
  <artifactId>camunda-bpm-data</artifactId>
  <version>${camunda-bpm-data.version}</version>
</dependency>
```

For Gradle Kotlin DSL add to your `build.gradle.kts`:

```kotlin
implementation("io.holunda.data:camunda-bpm-data:${camunda-bpm-data.version}")
```

### Declare process variable factories

First you have to define your process variables, by providing the variable name and type. For providing the type,
different convenience methods exist:

Here is an example in Java:

```java

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import static io.holunda.camunda.bpm.data.CamundaBpmData.*;

public class OrderApproval {
  public static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  public static final VariableFactory<Order> ORDER = customVariable("order", Order.class);
  public static final VariableFactory<Boolean> ORDER_APPROVED = booleanVariable("orderApproved");
  public static final VariableFactory<OrderPosition> ORDER_POSITION = customVariable("orderPosition", OrderPosition.class);
  public static final VariableFactory<BigDecimal> ORDER_TOTAL = customVariable("orderTotal", BigDecimal.class);
}
```

### Access process variables from Java Delegate

If you want to access the process variable, call methods on the `ProcessVariableFactory` to configure the usage context,
and then invoke the variable access methods.

Here is an example, how it looks like to access variable from `JavaDelegate` implemented in Java. In this example,
the total amount is calculated from the amounts of order positions and stored in the process variable.

```java

@Configuration
class JavaDelegates {

  @Bean
  public JavaDelegate calculateOrderPositions() {
    return execution -> {
      OrderPosition orderPosition = ORDER_POSITION.from(execution).get();
      BigDecimal oldTotal = ORDER_TOTAL.from(execution).getOptional().orElse(BigDecimal.ZERO);
      BigDecimal newTotal = oldTotal.add(orderPosition.getNetCost().multiply(BigDecimal.valueOf(orderPosition.getAmount())));
      ORDER_TOTAL.on(execution).setLocal(newTotal);
    };
  }
}
```

### Variable access from REST Controller

Now imagine you are implementing a REST controller for a user task form which
loads data from the process application, displays it, captures some input and
sends that back to the process application to complete the user task. By doing so,
you will usually need to access process variables. Here is an example:


```java

@RestController
@RequestMapping("/task/approve-order")
public class ApproveOrderTaskController {

    private final TaskService taskService;

    public ApproveOrderTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ApproveTaskDto> loadTask(@PathVariable("taskId") String taskId) {
        Order order = ORDER.from(taskService, taskId).get();
        return ResponseEntity.ok(new ApproveTaskDto(order));
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<Void> completeTask(@PathVariable("taskId") String taskId, @RequestBody ApproveTaskCompleteDto userInput) {
        VariableMap vars = builder()
            .set(ORDER_APPROVED, userInput.getApproved())
            .build();
        taskService.complete(taskId, vars);
        return ResponseEntity.noContent().build();
    }
}

```
