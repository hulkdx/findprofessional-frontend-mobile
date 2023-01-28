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
    namespace = "com.hulkdx.findprofessional.feature.authentication"

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
    implementation("io.insert-koin:koin-androidx-compose:${BuildDep.KOIN_COMPOSE_VERSION}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${BuildDep.ANDROIDX_LIFECYCLE}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${BuildDep.JUNIT_VERSION}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${BuildDep.JUNIT_VERSION}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${BuildDep.JUNIT_VERSION}")
    testImplementation("org.mockito:mockito-core:${BuildDep.MOCKITO_VERSION}")
    testImplementation("org.mockito:mockito-junit-jupiter:${BuildDep.MOCKITO_JUNIT5_VERSION}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDep.COROUTINES_TEST_VERSION}")

}
