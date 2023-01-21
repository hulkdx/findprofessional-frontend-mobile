plugins {
    // @formatter:off
    id("com.android.application")      version BuildDep.AGP_VERSION             apply false
    id("org.jetbrains.kotlin.android") version BuildDep.KOTLIN_VERSION          apply false

    // ./gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.44.0"
    // @formatter:on
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
