# Repository Guidelines

## Project Structure & Module Organization

This Maven monorepo contains holunda libraries for Camunda 7, primarily in Java and Kotlin.

- `c7-support/`: engine extensions, data APIs, deployment helpers, and REST clients.
- `c7-operations/`: administration and webapp utilities.
- `c7-testing/`: JGiven, Mockito, process coverage, and reporting plugins.
- `_examples/`: sample applications and integration scenarios.
- `_build/`: shared parent POM and aggregate coverage configuration.
- `docs/`: MkDocs guides and assets; `mkdocs.yml` configures the documentation site.

Modules use `src/main/java`, `src/main/kotlin`, and `src/main/resources`, with corresponding `src/test/` directories. Keep BPMN models and test fixtures in the appropriate resource directory.

## Build, Test, and Development Commands

Use JDK 21, as specified in `.java-version`, and the checked-in Maven wrapper. Run commands from the repository root unless noted.

- `./mvnw clean install`: build all modules, run unit tests, and install artifacts locally.
- `./mvnw -ntp clean package`: run the CI packaging build.
- `./mvnw -Pitest verify`: enable integration tests and verify their results.
- `./mvnw -pl c7-support/c7-data/core -am test`: test one module and its reactor dependencies.
- Add `-DskipExamples` to omit example modules and the aggregate coverage module.
- `mkdocs serve`: preview documentation locally after installing `docs/requirements.txt`.

## Coding Style & Naming Conventions

Follow `.editorconfig`: two-space indentation, UTF-8, LF endings, final newlines, and a 160-character line limit. Trim trailing whitespace except in Markdown. Use `UpperCamelCase` classes and `lowerCamelCase` methods and properties; preserve existing package names and module conventions. Document public APIs. Kotlin analysis uses `detekt.yml` and Codacy; frontend modules have local ESLint configuration.

## Testing Guidelines

Use JUnit 5; the parent POM bans JUnit 4. Follow existing Mockito and JGiven patterns where applicable. Name unit tests `*Test.java` or `*Test.kt`; integration tests use `*ITest` or `*IT`. Add regression tests for fixes. JaCoCo aggregates coverage under `_build/coverage-aggregator`; preserve coverage when changing behavior.

## Commit & Pull Request Guidelines

Branch from `develop` using `feature/<name>`, rebase before submission, and squash commits. Prefer semantic messages such as `fix(data): handle missing variables`; history commonly uses `build(deps): ...`. Describe the change, link relevant issues, report validation, and update documentation. Address CI and Codacy findings before review.

## Configuration & Credentials

Community Edition is the default Maven profile. Enterprise builds use `-Dcamunda-ee` and require credentials for `camunda-bpm-ee` in Maven settings. Keep credentials out of version control.
