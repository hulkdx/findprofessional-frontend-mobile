plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(projects.core)

            implementation("com.arkivanov.decompose:decompose:3.1.0")
            implementation("com.arkivanov.decompose:extensions-compose:3.1.0")
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.libs.navigation.decompose"
}
