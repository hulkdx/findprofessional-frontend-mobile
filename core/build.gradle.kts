plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.ktor.core)
            implementation(libs.androidx.dataStore.core)
            implementation(libs.coil.compose)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.core"
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.hulkdx.findprofessional.core.resources"
}
