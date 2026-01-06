plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.hulkdx.findprofessional.core"

        androidResources {
            enable = true
        }
    }
    sourceSets {
        commonMain.dependencies {
            implementation(compose.materialIconsExtended)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
            api(libs.kotlinx.serialization.json)
            api(libs.kotlinx.datetime)
            api(libs.lifecycle.viewmodel.compose)
            api(libs.ktor.core)
            api(libs.androidx.dataStore.core)
            api(libs.coil.compose)
            api(libs.coil.network.ktor)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.hulkdx.findprofessional.core.resources"
}
