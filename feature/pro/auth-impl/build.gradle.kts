plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.proApi)
            implementation(projects.feature.homeApi)
            api(projects.feature.pro.authApi)
            api(projects.feature.pro.scheduleApi)

            implementation(libs.ktor.core)
            implementation(libs.lifecycle.viewmodel.compose)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.pro.auth"
}
