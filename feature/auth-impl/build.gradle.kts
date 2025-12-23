plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            api(projects.feature.authApi)
            implementation(projects.feature.developerApi)
            implementation(projects.feature.homeApi)
            implementation(projects.feature.pro.scheduleApi)

            implementation(libs.ktor.core)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.kotlinx.datetime)
            implementation(libs.androidx.dataStore.core)
        }
        commonTest.dependencies {
            implementation(projects.libs.commonTests)
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlinx.coroutines.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.authentication"
}
