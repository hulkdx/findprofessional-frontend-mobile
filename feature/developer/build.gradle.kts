plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.proApi)

            // TODO: check these
            implementation(projects.feature.authImpl)
            implementation(projects.feature.homeImpl)
            implementation(projects.feature.reviewImpl)
            implementation(projects.feature.pro.authImpl)
            implementation(projects.feature.profileImpl)

            implementation(libs.ktor.core)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.kotlinx.datetime)
            implementation(libs.androidx.dataStore.core)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.developer"
}
