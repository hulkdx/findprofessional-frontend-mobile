plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(libs.lifecycle.viewmodel.compose)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.authentication"
}
