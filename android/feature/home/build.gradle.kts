plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.hulkdx.findprofessional.feature.home"
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

    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events("passed", "failed", "skipped", "standardOut", "standardError")
        }
    }
}

dependencies {
    implementation(project(":android:core"))
    implementation(project(":common"))

    implementation(platform("androidx.compose:compose-bom:${BuildDep.COMPOSE_BOM}"))
    implementation("io.insert-koin:koin-androidx-compose:${BuildDep.KOIN_COMPOSE_VERSION}")
}
