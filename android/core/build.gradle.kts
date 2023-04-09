plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = BuildDep.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildDep.MIN_SDK_VERSION
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = BuildDep.COMPOSE_VERSION
    }
    namespace = "com.hulkdx.findprofessional.core"
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:${BuildDep.COMPOSE_BOM}"))
    api("androidx.compose.material3:material3")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:${BuildDep.ANDROIDX_LIFECYCLE}")
    implementation("com.google.accompanist:accompanist-navigation-animation:${BuildDep.ACCOMPANIST_VERSION}")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
