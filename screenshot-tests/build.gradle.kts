plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.paparazzi)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // same dependencies as :app
            implementation(projects.core)
            implementation(projects.libs.navigationDecompose)

            implementation(projects.feature.authImpl)
            implementation(projects.feature.homeImpl)
            implementation(projects.feature.developerImpl)
            implementation(projects.feature.bookingImpl)
            implementation(projects.feature.profileImpl)
            implementation(projects.feature.reviewImpl)
            implementation(projects.feature.pro.authImpl)
            implementation(projects.feature.pro.scheduleImpl)
            implementation(projects.feature.pro.profileImpl)
            implementation(projects.feature.pro.availabilityImpl)

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
