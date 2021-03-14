plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.hulkdx.findprofessional"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    packagingOptions {
        // Required for Dependencies.COMPOSE_TEST
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    testOptions.unitTests.all {
        it.testLogging {
            events("passed", "failed", "skipped", "standardOut", "standardError")
        }
    }
}

dependencies {

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_ACTIVITY)

    testImplementation(Dependencies.JUNIT_API)
    testRuntimeOnly(Dependencies.JUNIT_ENGINE)
    testImplementation(Dependencies.JUNIT_PARAM)
    testImplementation(Dependencies.MOCKITO)
    testImplementation(Dependencies.COROUTINES_TEST)

    androidTestImplementation(Dependencies.COMPOSE_TEST)

}
