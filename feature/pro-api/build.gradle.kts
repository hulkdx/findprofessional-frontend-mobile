plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.hulkdx.findprofessional.feature.pro.api"
    }
    sourceSets {
        commonMain.dependencies {
            api(projects.core)
            api(projects.feature.authApi)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
    }
}

