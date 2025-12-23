plugins {
    alias(libs.plugins.hulkdx.kmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.profile.api"
}
