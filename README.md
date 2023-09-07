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

This is always referencing the `.default.env` file, even if the `dotenv.filename` environment variable is set to `dev` or `prod`.

In previous versions of Sonar Scanner Gradle this was working as expected.

## Steps to reproduce

1. Clone this repository
2. `$ export ENV=default`
3. Run `make sonar`, this is working
4. `$ export ENV=staging`
5. Run `make sonar`, this is failing

## Possible fix

Downgrade to `4.2.0.3129` and run again the steps