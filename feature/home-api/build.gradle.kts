plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.proApi)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.home.api"
}
