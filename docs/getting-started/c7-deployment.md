## Why should I use this?

This library offers an alternative way to deploy processes and decisions (and even cases) 
to Camunda 7 and replaces the default Camunda auto deployment mechanism.

Why would you want to do that? There are mainly two reasons:

* Camunda's auto deployment cannot handle multi-tenant deployments within SpringBoot applications, 
as it does not properly discover the resources within the repackaged JAR
* The default way to specify process archives in `processes.xml` is not as easy and SpringBoot-like 
as defining it in YAML (which this library allows)

## How to start?

### Install dependency

Add the dependency to your project:

=== "Maven"

    Add to your `pom.xml`

    ```xml
    <dependency>
      <groupId>io.holunda.deployment</groupId>
      <artifactId>camunda-bpm-spring-boot-deployment</artifactId>
    </dependency>
    ```

=== "Gradle"

    Add to your `build.gradle.kts`

    ```kts
    implementation("io.holunda.deployment:camunda-bpm-spring-boot-deployment:$version")
    
    ```

### Disable Camunda 7 Auto Deployment

!!! note
    Make sure your application doesn't contain `processes.xml` on your classpath.

In addition, set the following properties in your `application.yaml`:

```yaml title="application.yaml"
camunda:
  bpm:
    deployment:
      enabled: false
```

### Configure

Set-up the tenants and folders to deploy from in your application properties.

```yaml title="application.yaml"
camunda:
  bpm:
    deployment:
      archives:
        - name: Default
          path: tenants/default
        - name: TenantOne
          tenant: one
          path: tenants/one

```
