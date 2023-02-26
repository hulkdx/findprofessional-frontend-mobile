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
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    namespace = "com.hulkdx.findprofessional"
}

dependencies {

    implementation(project(":android:core"))
    implementation(project(":android:feature:authentication"))
    implementation(project(":common"))

    implementation(platform("androidx.compose:compose-bom:${BuildDep.COMPOSE_BOM}"))
    implementation("com.google.accompanist:accompanist-navigation-animation:${BuildDep.ACCOMPANIST_VERSION}")

    implementation("io.insert-koin:koin-core:${BuildDep.KOIN_VERSION}")
    implementation("io.insert-koin:koin-android:${BuildDep.KOIN_VERSION}")
    implementation("io.insert-koin:koin-androidx-compose:${BuildDep.KOIN_COMPOSE_VERSION}") {
        exclude("androidx.navigation")
    }

    androidTestImplementation(platform("androidx.compose:compose-bom:${BuildDep.COMPOSE_BOM}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:${BuildDep.LEAK_CANARY_VERSION}")
}
