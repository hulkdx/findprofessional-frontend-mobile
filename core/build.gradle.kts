plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
            api(libs.koin.compose)

            api(libs.kotlinx.datetime)
            api(libs.kotlinx.serialization.json)

            api(libs.lifecycle.viewmodel.compose)

            api(libs.ktor.core)

            implementation(libs.androidx.dataStore.core)
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
