plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hulkdx.findprofessional"
        minSdk = 29
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    namespace = "com.hulkdx.findprofessional"
}

dependencies {
    implementation(project(":common"))
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
