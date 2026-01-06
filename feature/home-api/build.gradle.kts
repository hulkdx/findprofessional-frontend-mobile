plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.hulkdx.findprofessional.feature.home.api"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.proApi)
        }
    }
}

