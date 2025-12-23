plugins {
    alias(libs.plugins.hulkdx.kmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            api(projects.feature.proApi)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.feature.review.api"
}
