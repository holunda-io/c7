# Import - Inbox for existing projects

## How to proceed

### Step 1: import

* run function `import_repo_into_monorepo` from `.console.sh` to clone the old-repo and import it to `_import/old-repo-name`.
* usage: `source .console.sh && import_repo_into_monorepo old-repo-name old-repo-github-url`

## Step 2: clean up

* check for unexpected additional files/entries in 
  * `.mvn`
  * `.javaversion`(=17)
  * `.editorconfig`
  * `.gitignore`
  * `LICENSE`
  * `.github`
* `rm -rf .git .mvn mvnw* .java-version .editorconfig LICENSE .github .gitignore`

## Step 3: include in multi-module structure

* Does the lib already use our `maven-parent-kotlin-base`?
  * Yes: Fine
  * No: need to migrate (remove many, many lines of XML)
* Copy missing contributors to root pom and remove developers
* remove all repository, scm, ... elements already provided by root

use parent

```xml
<parent>
    <groupId>io.holunda.c7._</groupId>
    <artifactId>c7-parent</artifactId>
    <version>2025.05.0-SNAPSHOT</version>
    <relativePath>../../_build/parent/pom.xml</relativePath>
</parent>

```

* include in root/modules
