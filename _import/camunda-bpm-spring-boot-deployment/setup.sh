#!/usr/bin/env bash

GITHUB_ORGANIZATION=toolisticon
GITHUB_REPOSITORY=kotlin-lib
BASE_GROUP_ID=io.toolisticon.template
BASE_DESCRIPTION=$GITHUB_REPOSITORY
BASE_ARTIFACT_ID=$GITHUB_REPOSITORY

for file in `find . \( -name "pom.xml" -o -name "*.kt" -o -name "*.md" \) -type f`
do
  sed -i '' "s/GITHUB_ORGANIZATION/${GITHUB_ORGANIZATION}/g" $file
  sed -i '' "s/GITHUB_REPOSITORY/${GITHUB_REPOSITORY}/g" $file
  sed -i '' "s/BASE_GROUP_ID/${BASE_GROUP_ID}/g" $file
  sed -i '' "s/BASE_ARTIFACT_ID/${BASE_ARTIFACT_ID}/g" $file
  sed -i '' "s/BASE_DESCRIPTION/${BASE_DESCRIPTION}/g" $file
done
