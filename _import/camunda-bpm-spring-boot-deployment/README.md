# BASE_ARTIFACT_ID

Template repository for usage in organizations: toolisticon, holunda-io, holixon...

[![Build Status](https://github.com/GITHUB_ORGANIZATION/GITHUB_REPOSITORY/workflows/Development%20branches/badge.svg)](https://github.com/GITHUB_ORGANIZATION/GITHUB_REPOSITORY/actions)
[![sponsored](https://img.shields.io/badge/sponsoredBy-Holisticon-RED.svg)](https://holisticon.de/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/BASE_GROUP_ID/BASE_ARTIFACT_ID/badge.svg)](https://maven-badges.herokuapp.com/maven-central/BASE_GROUP_ID/BASE_ARTIFACT_ID)

This repository is a **template repository** designed to be a template for the next project.

## How to use

* create a new repo on github (can be in any organization). Choose this project as template repository. Copy all branches, so the `master`exists in your repo (for the github actions)
* on the command line: clone your new repo locally
* in the `setup.sh` script: set your organization, repository and base package
* run the `setup.sh` script, all placeholders are filled with your information
* delete the setup-script
* Update the `README.md`
* in the `developers` section of the `pom.xml`: mention yourself ... it is your project.

## Things to change after usage of template

To change the following values, modify the placeholders in `setup.sh` and run it.
This is a one-time operation, you can safely delete the `setup.sh` file afterwards.

Of course, you can also edit manually .... and do not forget to change this `README.md` with YOUR project specific information :-).

### Maven pom.xml 

* Maven coordinates: `groupId`, `artifactId` and `version`
* Main description: `name`, `url`, `description`
* SCM: `connection`, `url`, `developerConnection`

### Issue Template

* correct the URL to repo

### Issue Labels

* Check the release-notes.yml for details, but create the following labels: Type: dependencies, Type: bug, Type: documentation, Type: question, Type: enhancement

