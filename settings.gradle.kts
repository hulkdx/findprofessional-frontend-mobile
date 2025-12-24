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

include(":feature:auth-api")
include(":feature:auth-impl")
include(":feature:home-api")
include(":feature:home-impl")
include(":feature:developer-api")
include(":feature:developer-impl")
include(":feature:booking-api")
include(":feature:booking-impl")
include(":feature:profile-api")
include(":feature:profile-impl")
include(":feature:review-api")
include(":feature:review-impl")
include(":feature:pro-api")
include(":feature:pro:auth-api")
include(":feature:pro:auth-impl")
include(":feature:pro:schedule-api")
include(":feature:pro:schedule-impl")
include(":feature:pro:availability-api")
include(":feature:pro:availability-impl")
include(":feature:pro:profile-api")
include(":feature:pro:profile-impl")

include(":screenshot-tests")
