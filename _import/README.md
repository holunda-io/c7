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
* `rm -rf .git .mvn mvnw* .javaversion .editorconfig LICENSE .github `

