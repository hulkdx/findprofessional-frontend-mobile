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

            implementation(libs.lifecycle.viewmodel.compose)

            // TODO: should you remove these?
            implementation(libs.androidx.dataStore.core)

            implementation(libs.ktor.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)
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
