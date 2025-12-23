plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core)
            api(projects.feature.authApi)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.pro.api"
}
