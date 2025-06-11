# Camunda Platform 7 WebApp Auto-Login

*Auto-login feature for development*

[![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/holisticon#open-source-lifecycle)

## Why should you use it?

Because otherwise, you need to type again and again "admin/admin" or "demo/demo" when developing locally and trying to log-in to local Camunda Webapp.

## Feature

Allows Camunda WebApp auto-login by the user configured in the application properties.

## Usage

Assuming you are using Camunda Spring Boot Starter, put the following dependency on your classpath:

```xml
<dependency>
  <groupId>io.holunda</groupId>
  <artifactId>camunda-platform-7-autologin</artifactId>
</dependency>
```

Activate the auto-login in your `application.yaml`: 

```yml
camunda:
  bpm:
    login:
      enabled: true
      user-id: admin
```

That's it, any user accessing Camunda webapp is now authenticated as 'admin'. Be careful not to use this in production! 

## Additional configuration

There are some more properties, you may need:

```yml
camunda:
  bpm:
    login:
      enabled: true                     # enables the feature, disabled by default
      user-id: admin                    # user id of the user, defaults to 'nobody'
      camunda-context-path: /some-path  # path camunda webapp bound to, defaults to '/camunda'
      create-if-absent: true            # will create a dummy user using internal identity service, defaults to 'false'
      random-password: false            # flag to control the password of the auto-generated-user, defaults to 'true'. 
                                        # If false is selected, the password is equals to user id.             
```
