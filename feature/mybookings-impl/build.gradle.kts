plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            api(projects.feature.mybookingsApi)
            implementation(projects.feature.authApi)

            implementation(libs.lifecycle.viewmodel.compose)
            implementation(compose.materialIconsExtended)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.mybookings"
}
