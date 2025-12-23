plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            api(projects.feature.homeApi)
            implementation(projects.feature.proApi)
            implementation(projects.feature.bookingApi)
            implementation(projects.feature.reviewApi)

            implementation(libs.ktor.core)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(projects.libs.commonTests)
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.home"
}
