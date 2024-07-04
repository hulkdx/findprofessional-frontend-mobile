plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
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
