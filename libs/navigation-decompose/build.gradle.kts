plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.core)

            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.libs.navigation.decompose"
}
