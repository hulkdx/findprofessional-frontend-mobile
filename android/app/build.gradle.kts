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
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isDebuggable = false
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
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    androidTestImplementation(libs.ktor.mock)
    androidTestImplementation(libs.androidx.dataStore.core)
    androidTestImplementation(libs.androidx.espresso.core)
    //androidTestImplementation(libs.androidx.uiautomator)
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0-alpha03")
}
