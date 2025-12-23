plugins {
    alias(libs.plugins.hulkdx.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.booking)

            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.libs.common.tests"
}
