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
                "proguard-rules.pro"
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
    implementation(project(":common-kmm"))

    implementation(platform("androidx.compose:compose-bom:${BuildDep.COMPOSE_BOM}"))
    implementation(BuildDep.COMPOSE_MATERIAL)
    implementation(BuildDep.KOIN_COMPOSE)

    testImplementation(BuildDep.JUNIT_API)
    testRuntimeOnly(BuildDep.JUNIT_ENGINE)
    testImplementation(BuildDep.JUNIT_PARAM)
    testImplementation(BuildDep.MOCKITO)
    testImplementation(BuildDep.MOCKITO_JUNIT5)
    testImplementation(BuildDep.COROUTINES_TEST)

}
