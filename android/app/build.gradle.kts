plugins {
    alias(libs.plugins.hulkdx.android.application.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hulkdx.findprofessional"

    defaultConfig {
        applicationId = namespace
        targetSdk = 34
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "failed", "skipped", "standardOut", "standardError")
    }
}

dependencies {
    implementation(project(":android:core"))
    implementation(project(":android:feature:authentication"))
    implementation(project(":android:feature:home"))
    implementation(project(":android:feature:developer"))
    implementation(project(":android:feature:profile"))
    implementation(project(":android:feature:navigation"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    debugImplementation(libs.leakcanary)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.ktor.mock)
    androidTestImplementation(libs.androidx.dataStore.core)
}
