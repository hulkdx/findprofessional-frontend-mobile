pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
    }
}

include(":android:app")
include(":ios")
include(":composeApp")

include(":android:paparazzi-tests")

include(":android:feature:authentication")
include(":android:feature:developer")
include(":android:feature:home")
include(":android:feature:navigation")
include(":android:feature:profile")
include(":android:feature:review")
include(":android:feature:book")

