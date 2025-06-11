## Motivation

Camunda 7 Web Apps use Identity Service to grant access to the resources. Too simple for must real-world scenarios,
it is time-wasting during the development process. Most of the time the developer either uses serious replacement
for the embedded identity service (like OAuth2 login) or has to type user/password on every Web App access.
Especially with the newest generation of the browsers detects the combination of `demo/demo` and `admin/admin` as 
too trivial and insecure and proposing to change the dummy local development password after ever login-

## Solution

This library provides a solution to hook into the authentication filter used by Camunda 7 and perform a login with a username
configured in the application properties.

## Features

- Creates Camunda `Authentication` with the username provided in the properties
- Optionally configures a different context path (default is `/camunda`)
- Optionally creates the user if he/she doesn't exist in `IdentityService`
- Optionally generates a random password for the user

