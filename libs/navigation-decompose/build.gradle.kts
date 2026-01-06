plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.hulkdx.findprofessional.libs.navigation.decompose"
    }
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.core)

            implementation(projects.feature.authApi)

            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.lifecycle.viewmodel.compose)
        }
    }
}

