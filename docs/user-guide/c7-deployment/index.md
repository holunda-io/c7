---
title: C7 Deployment User Guide
---

## Motivation

This library offers an alternative way to deploy processes and decisions to Camunda BPM and replaces the default Camunda auto deployment
mechanism.

Why would you want to do that? There are mainly two reasons:

* Camunda's auto deployment cannot handle multi tenant deployments within SpringBoot applications, as it does not properly discover the resources within the
  repackaged JAR
* The default way to specify process archives in `processes.xml` is not as easy and SpringBoot-like as defining it in YAML (which this library allows)

If you want to know more about the issue with SpringBoot, you can
read [this article on medium](https://medium.com/holisticon-consultants/multi-tenant-deployments-with-camunda-bpm-and-springboot-ecac2c8826f8).

## Activation

The library configures itself by Spring Autoconfiguration and is enabled by default, if necessary you can still disable the deployment mechanism by adding the
following property to your `application.yaml`:

```yaml
camunda:
  bpm:
    deployment:
      enabled: false
```

!!! note
    
    Make sure you disable Camunda's auto deployment! It is as easy as just deleting the `processes.xml`.

## Configuration

Instead of `processes.xml` you have to configure the process archives in your `application.yaml`:

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

If you don't specify a `tenant`, the tenant within Camunda will be `null`, which is the Camunda default.

You have to place your resources in a folder structure that matches the specified archives:

```
src/main/resources
  /tenants
    /default
      - firstProcess.bpmn
    /one
      - secondProcess.bpmn
```

It is possible that your tenant paths intersect. For example a common scenario is to have the default process application (without tenant) and then introduce a tenant contained in a certain folder at some day. This feature is deactivated by
default to prevent misconfiguration and needs to be activated by setting `allow-overlapping` to `true`, configuring the archives accordingly. The configuration would look like this:

```yaml title="application.yaml"
camunda:
  bpm:
    deployment:
      allow-overlapping: true
      archives:
        - name: Default
          path: # left empty to address the root of the resources folder and all subfolders.
        - name: TenantOne
          tenant: tenant-one
          path: one # subfolder of the root resources folder 
```
As a result, all Camunda relevant files from the root of the classpath `src/main/resources` and all subdirectories, excluding `one` are deployed in a default archive (without a tenant). All resources from `src/main/resources/one` are deployed ina a second archive with tenant `tenant-one`.

To deploy multiple folders for the same tenant the tenant uniqueness check needs to be disabled:

```yaml title="application.yaml"
camunda:
  bpm:
    deployment:
      allow-overlapping: true
      check-tenant-archive-uniqueness: false
      archives:
        - name: BPMN
          path: bpmn
        - name: DMN
          path: dmn
```

