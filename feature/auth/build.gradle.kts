plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)

            implementation(libs.ktor.core)
            implementation(libs.androidx.dataStore.core)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.authentication"
}
