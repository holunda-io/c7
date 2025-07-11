---
title: Webapp Auto Login
description: Getting Started Guide for Webapp Auto Login
# icon: material/emoticon-happy
status: new
---

## Why should you use it?

Because otherwise, you need to type again and again "admin/admin" or "demo/demo" 
when developing locally and trying to log-in to local Camunda Webapp.

!!! danger

    Important, never use this in production environment, since everyone can login to Camunda Webapp and cause harm to your system.

## How to start?

### Requirements

- Spring Boot
- Camunda 7

### Install dependency

```xml
<dependency>
  <groupId>io.holunda</groupId>
  <artifactId>camunda-platform-7-autologin</artifactId>
</dependency>
```

### Add some configuration

Activate the auto-login in your `application.yaml`:

```yml
camunda:
  bpm:
    login:
      enabled: true
      user-id: admin
```

### Use it

Navigate to the URL of Camunda Webapp (Cockpit/Tasklist/Admin) you and will be already logged in.

