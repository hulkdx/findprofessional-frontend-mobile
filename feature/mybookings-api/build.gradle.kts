plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.hulkdx.findprofessional.feature.mybookings.api"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
        }
    }
}

