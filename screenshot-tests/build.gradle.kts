plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
    id("app.cash.paparazzi") version "1.3.5"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // same dependencies as :app
            implementation(projects.core)
            implementation(projects.libs.navigationDecompose)

            implementation(projects.feature.auth)
            implementation(projects.feature.home)
            implementation(projects.feature.developer)
            implementation(projects.feature.book)
            implementation(projects.feature.profile)
            implementation(projects.feature.review)
            implementation(projects.feature.pro.auth)

            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)
            implementation(libs.androidx.dataStore.core)
        }

        commonTest.dependencies {
            implementation(projects.libs.commonTests)
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.tools.screenshot.tests"
}

// Disable release build type
androidComponents {
    beforeVariants { variant ->
        variant.enable = variant.buildType == "debug"
    }
}
