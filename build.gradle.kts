plugins {
    base
    id("co.uzzu.dotenv.gradle") version "2.0.0"
    id("org.sonarqube") version "4.3.1.3277"
}

sonar {
    properties {
        property("sonar.projectKey", "reproducer")
        property("sonar.organization", "$YOUR_ORG")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

println(env.FOO.value)