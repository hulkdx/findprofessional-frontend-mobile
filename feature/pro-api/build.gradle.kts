plugins {
    alias(libs.plugins.hulkdx.kmp.library)
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
