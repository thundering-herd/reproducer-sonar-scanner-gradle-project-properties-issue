# Reproducer for Sonar Scanner Gradle Issue

## Description

This is a reproducer for the issue described
in [Sonar Community Forum](https://community.sonarsource.com/t/sonar-scanner-4-3-1-3277-ignoring-project-properties/98581).

## Issue

Sonar Scanner Gradle ignores project properties when updating to version 4.3.1.3277.
In this example project the `gradle.properties` file contains the following line:

```properties
dotenv.filename=.default.env
```

This is always referencing the `.default.env` file, even if the `dotenv.filename` environment variable is set to `dev`
or `prod`.

In previous versions of Sonar Scanner Gradle this was working as expected.

## Steps to reproduce

### Prerequisites
* Gradle 8.1.1.
* Kotlin 
* Terminal
* Tested on macOS 13.5.2

1. Clone this repository
2. Create a manual SonarCloud project on `sonarcloud.io` and transfer following properties to your project:
    - `ORGANIZATION KEY` from your Information page
    - `PROJECT KEY` from your Information page
    - `SONAR TOKEN` from the CI/CD integration page for GitHub Actions
3. Set the following Sonar Properties in `build.gradle.kts` accordingly to your SonarCloud project:
    - `$YOUR_ORG` set to `ORGANIZATION KEY`
    - `$YOUR_PROJECT_KEY` set to `PROJECT KEY`
4. For a successful run of the Sonar Scanner Gradle you need to following in your terminal:

```shell
$ export SONAR_TOKEN=(add here the SONAR TOKEN from your SonarCloud project)
$ touch .default.env
$ echo "FOO=default" > .default.env
$ ./gradlew sonar --stacktrace -Pdotenv.filename=./.default.env
$ rm .default.env
```

5. For a failing run of the Sonar Scanner Gradle you need to following in your terminal:

```shell
$ export SONAR_TOKEN=(add here the SONAR TOKEN from your SonarCloud project)
$ touch .staging.env
$ echo "FOO=staging" > .staging.env
$ ./gradlew sonar --stacktrace -Pdotenv.filename=./.staging.env
$ rm .staging.env
```

## Possible fix

Downgrade to `4.2.0.3129` and run again the steps from 5.