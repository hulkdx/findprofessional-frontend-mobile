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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":ios")
include(":app")
include(":core")

include(":libs:navigation-decompose")
include(":libs:common-tests")

include(":feature:auth")
include(":feature:home")
include(":feature:developer")
include(":feature:book")
include(":feature:profile")
include(":feature:review")
include(":feature:pro:auth")
include(":feature:pro:home")

include(":screenshot-tests")
