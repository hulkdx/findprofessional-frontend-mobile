plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    namespace = "com.hulkdx.findprofessional.core"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.navigation.compose)
    api(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.coil.compose)

    debugApi(libs.androidx.compose.ui.tooling)
}
