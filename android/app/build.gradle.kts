plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = BuildDep.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.hulkdx.findprofessional"
        minSdk = BuildDep.MIN_SDK_VERSION
        targetSdk = BuildDep.COMPILE_SDK_VERSION
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = BuildDep.COMPOSE_VERSION
    }

    packagingOptions {
        // Required for Dependencies.COMPOSE_TEST
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(project(":android:core"))
    implementation(project(":android:feature:authentication"))
    implementation(project(":common-kmm"))

    implementation(BuildDep.CORE_KTX)
    implementation(BuildDep.COMPOSE_UI)
//    implementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(BuildDep.COMPOSE_ACTIVITY)
    implementation(BuildDep.COMPOSE_NAVIGATION)
    implementation(BuildDep.KOIN_CORE)
    implementation(BuildDep.KOIN_ANDROID)
    implementation(BuildDep.KOIN_COMPOSE)

    androidTestImplementation(BuildDep.COMPOSE_TEST)
    androidTestImplementation(BuildDep.EXT_JUNIT_TEST)

    debugImplementation(BuildDep.LEAK_CANARY)

}
