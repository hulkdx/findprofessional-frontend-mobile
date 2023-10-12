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
        sourceCompatibility = BuildDep.JAVA_VERSION_SOURCE_COMPATIBILITY
        targetCompatibility = BuildDep.JAVA_VERSION_TARGET_COMPATIBILITY
    }

    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation("androidx.navigation:navigation-compose:${BuildDep.COMPOSE_NAVIGATION}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${BuildDep.ACCOMPANIST_VERSION}")

    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("io.coil-kt:coil-compose:${BuildDep.COIL_VERSION}")
}
