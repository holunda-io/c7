## All configuration parameters

```yml
camunda:
  bpm:
    login:
      enabled: true                     # enables the feature, disabled by default
      user-id: admin                    # user id of the user, defaults to 'nobody'
      camunda-context-path: /some-path  # path camunda webapp bound to, defaults to '/camunda'
      create-if-absent: true            # will create a dummy user using internal 
                                        # identity service, defaults to 'false'
      random-password: false            # flag to control the password of the auto-generated-user, 
                                        # defaults to 'true'. 
                                        # If false is selected, the password is equals to user id.             
```

## Typical configuration

A typical configuration is depicted below. We use the Camunda `admin-user` feature to create the user and login as such.

```yaml
camunda:
  bpm:
    admin-user:                         # Create the admin user 
      id: admin
      email: admin@localhost
      first-name: Admin
      last-name: Administratus
      password: admin
    login:                              # Autologin
      enabled: true            
      user-id: admin

```
