plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.hulkdx.findprofessional.libs.common.tests"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.bookingApi)
            implementation(projects.feature.bookingImpl)
            implementation(projects.feature.proApi)

            implementation(libs.kotlinx.datetime)
        }
    }
}

